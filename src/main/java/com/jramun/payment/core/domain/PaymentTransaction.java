package com.jramun.payment.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name = "payment_transaction", indexes = {
        @Index(columnList = "factor_number", name = "payment_transaction_idx_01", unique = true),
        @Index(columnList = "payment_gateway_type", name = "payment_transaction_idx_02"),
        @Index(columnList = "card_number", name = "payment_transaction_idx_03"),
        @Index(columnList = "status", name = "payment_transaction_idx_04"),
        @Index(columnList = "token", name = "payment_transaction_idx_05")
})
public class PaymentTransaction extends BaseEntity {

    @Column(name = "token")
    private String token;
    @Column(name = "factor_number")
    private String factorNumber;
    @Column(name = "payment_gateway_type")
    private String paymentGatewayType;
    @Column(name = "amount")
    private double amount;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "status")
    private String status;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "description")
    private String description;

    public PaymentTransaction(String token, String factorNumber, String paymentGatewayType,
                              double amount, String mobile, String description) {
        this.token = token;
        this.factorNumber = factorNumber;
        this.paymentGatewayType = paymentGatewayType;
        this.amount = amount;
        this.mobile = mobile;
        this.description = description;
    }

    @Override
    @Column(name = "payment_transaction_id")
    public long getId() {
        return id;
    }

    public String getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(String factorNumber) {
        this.factorNumber = factorNumber;
    }

    public String getPaymentGatewayType() {
        return paymentGatewayType;
    }

    public void setPaymentGatewayType(String paymentGatewayType) {
        this.paymentGatewayType = paymentGatewayType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
