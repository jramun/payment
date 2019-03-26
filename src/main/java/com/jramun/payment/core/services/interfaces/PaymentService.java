package com.jramun.payment.core.services.interfaces;


import com.jramun.payment.core.enumeration.PaymentGatewayType;

public interface PaymentService {

    void createTransaction(String token, String factorNumber,
                           double amount, String description,
                           String mobile, PaymentGatewayType type);

    void successTransaction(String token, String factorNumber);

    void failedTransaction(String token, String factorNumber);

    void deleteTransaction(String token,String factorNumber);

    String verification(String token, String factorNumber);

    boolean validate(String token, String factorNumber);
}