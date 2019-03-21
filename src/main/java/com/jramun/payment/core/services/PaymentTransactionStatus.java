package com.jramun.payment.core.services;

public enum PaymentTransactionStatus {
    SUCCESS("SUCCESS"), FIELD("FIELD");
    private String val;

    PaymentTransactionStatus(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
