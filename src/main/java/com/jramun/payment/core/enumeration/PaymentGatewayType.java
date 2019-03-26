package com.jramun.payment.core.enumeration;

public enum PaymentGatewayType {

    PAY("PAY"), ZARINPAL("ZARINPAL"), MELAT("MELAT");

    private String val;

    PaymentGatewayType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
