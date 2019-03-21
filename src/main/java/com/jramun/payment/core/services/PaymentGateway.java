package com.jramun.payment.core.services;

public enum PaymentGateway {

    PAY("PAY"), ZARINPAL("ZARINPAL"), MELAT("MELAT");

    private String val;

    PaymentGateway(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
