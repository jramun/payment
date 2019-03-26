package com.jramun.payment.modules.pay.exceptions;

public class PayVerifyException extends RuntimeException {

    private String token;
    private String factorNumber;
    private String message;

    public PayVerifyException(String token, String factorNumber, String message) {
        this.token = token;
        this.factorNumber = factorNumber;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(String factorNumber) {
        this.factorNumber = factorNumber;
    }
}
