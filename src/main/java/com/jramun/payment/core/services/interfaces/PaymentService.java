package com.jramun.payment.core.services.interfaces;


import com.jramun.payment.core.services.PaymentGateway;

public interface PaymentService {

    void createTransaction(String token, String factorNumber, double amount, String description,
                           String mobile, PaymentGateway type);

    void successTransaction(String token, String factorNumber);

    void failedTransaction(String token, String factorNumber);
}