package com.jramun.payment.core.services.implementation;

import com.jramun.payment.core.domain.PaymentTransaction;
import com.jramun.payment.core.exceptions.PaymentException;
import com.jramun.payment.core.exceptions.Status;
import com.jramun.payment.core.repositories.TransactionRepository;
import com.jramun.payment.core.services.PaymentGateway;
import com.jramun.payment.core.services.PaymentTransactionStatus;
import com.jramun.payment.core.services.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PaymentServiceImp implements PaymentService {

    @Value("${pay.expire-token}")
    private long expireToken;
    private TransactionRepository repository;

    @Autowired
    public PaymentServiceImp(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void createTransaction(String token, String factorNumber, double amount,
                                  String description, String mobile, PaymentGateway type) {
        if (!repository.existsByFactorNumber(factorNumber) || !repository.existsByToken(token))
            throw new PaymentException(Status.EXCEPTION, "token,factor number notfound");
        PaymentTransaction transaction = new PaymentTransaction(token, factorNumber, type.getVal(),
                amount, mobile, description);
        repository.save(transaction);
    }

    @Override
    @Transactional
    public void successTransaction(String token, String factorNumber) {
        if (!repository.existsByToken(token) || !repository.existsByFactorNumber(factorNumber))
            throw new PaymentException(Status.EXCEPTION, "token,factor number notfound");
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (!transaction.getToken().equals(token))
            throw new PaymentException(Status.EXCEPTION, "token,factor number invalid");
        if (new Date().getTime() - transaction.getCreatedDate().getTime() <= expireToken)
            throw new PaymentException(Status.EXCEPTION, "token expire");
        transaction.setStatus(PaymentTransactionStatus.SUCCESS.getVal());
        repository.save(transaction);
    }

    @Override
    @Transactional
    public void failedTransaction(String token, String factorNumber) {
        if (!repository.existsByToken(token) || !repository.existsByFactorNumber(factorNumber))
            throw new PaymentException(Status.EXCEPTION, "token,factor number notfound");
        PaymentTransaction transaction = repository.findByFactorNumber(factorNumber);
        if (!transaction.getToken().equals(token))
            throw new PaymentException(Status.EXCEPTION, "token,factor number invalid");
        if (new Date().getTime() - transaction.getCreatedDate().getTime() <= expireToken)
            throw new PaymentException(Status.EXCEPTION, "token expire");
        transaction.setStatus(PaymentTransactionStatus.FIELD.getVal());
        repository.save(transaction);
    }
}
