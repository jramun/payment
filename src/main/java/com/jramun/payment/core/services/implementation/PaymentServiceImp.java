package com.jramun.payment.core.services.implementation;

import com.jramun.payment.core.domain.PaymentTransaction;
import com.jramun.payment.core.enumeration.PaymentGatewayType;
import com.jramun.payment.core.enumeration.PaymentTransactionStatus;
import com.jramun.payment.core.exceptions.Status;
import com.jramun.payment.core.exceptions.TransactionException;
import com.jramun.payment.core.repositories.TransactionRepository;
import com.jramun.payment.core.services.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SuppressWarnings("ALL")
@Service
public class PaymentServiceImp implements PaymentService {

    @Value("${pay.expire-token-date}")
    private long expireToken;
    private TransactionRepository repository;

    @Autowired
    public PaymentServiceImp(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void createTransaction(String token, String factorNumber, double amount,
                                  String description, String mobile, PaymentGatewayType type) {
        if (repository.existsByFactorNumber(factorNumber) ||
                repository.existsByToken(token))
            throw new TransactionException(Status.EXIST_TRANSACTION, "token,factor error");
        PaymentTransaction transaction = new PaymentTransaction
                (token, factorNumber, type.getVal(),
                        amount, mobile, description);
        repository.save(transaction);
    }

    @Override
    @Transactional
    public void successTransaction(String token, String factorNumber) {
        if (!validation(token, factorNumber))
            throw new TransactionException(Status.NOT_EXIST_TRANSACTION, "token|factor error");
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (transaction.isDeleted())
            throw new TransactionException(Status.DELETE_TRANSACTION, "transaction is delete");
        if (!transaction.getStatus().isEmpty())
            throw new TransactionException(Status.STATUS_TRANSACTION, "transaction status is set, cannot set new status");
        transaction.setStatus(PaymentTransactionStatus.SUCCESS);
        repository.save(transaction);
    }

    @Override
    @Transactional
    public void failedTransaction(String token, String factorNumber) {
        if (!validation(token, factorNumber))
            throw new TransactionException(Status.NOT_EXIST_TRANSACTION, "token|factor error");
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (transaction.isDeleted())
            throw new TransactionException(Status.DELETE_TRANSACTION, "transaction is delete");
        if (!transaction.getStatus().isEmpty())
            throw new TransactionException(Status.STATUS_TRANSACTION, "transaction status is set, cannot set new status");
        transaction.setStatus(PaymentTransactionStatus.FIELD);
        repository.save(transaction);
    }

    @Override
    public void deleteTransaction(String token, String factorNumber) {
        if (!validation(token, factorNumber))
            throw new TransactionException(Status.NOT_EXIST_TRANSACTION, "token|factor error");
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (transaction.isDeleted())
            throw new TransactionException(Status.DELETE_TRANSACTION, "transaction is delete, cannot delete error");
        transaction.setDeleted(true);
        repository.save(transaction);
    }

    @Override
    @Transactional
    public boolean validate(String token, String factorNumber) {
        return validation(token, factorNumber);
    }

    private boolean validation(String token, String factorNumber) {
        if (!repository.existsByToken(token) || !repository.existsByFactorNumber(factorNumber))
            return false;
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (!transaction.getToken().equals(token))
            return false;
        if (new Date().getTime() - transaction.getCreatedDate().getTime() >= expireToken)
            return false;
        return true;
    }
}
