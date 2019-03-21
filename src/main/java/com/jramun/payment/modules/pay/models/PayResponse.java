package com.jramun.payment.modules.pay.models;

public class PayResponse {

    private double amount;

    private String mobile;

    private String factorNumber;

    private String description;

    private String token;

    private String redirect;

    public PayResponse initiate(double amount, String factorNumber, String mobile,
                                String transId, String redirect, String description) {
        this.amount = amount;
        this.factorNumber = factorNumber;
        this.mobile = mobile;
        this.description = description;
        this.token = transId;
        this.redirect = redirect;
        return this;
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

    public String getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(String factorNumber) {
        this.factorNumber = factorNumber;
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

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
