package com.jramun.payment.core.repositories;


import com.jramun.payment.core.domain.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<PaymentTransaction, Long> {

    boolean existsByFactorNumber(String factorNumber);

    PaymentTransaction findByFactorNumber(String factorNumber);

    List<PaymentTransaction> findByCardNumber(String cardNumber);

    PaymentTransaction findByToken(String token);

    boolean existsByToken(String token);
}
