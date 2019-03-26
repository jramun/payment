package com.jramun.payment.core.exceptions;

public class TransactionException extends RuntimeException {
    private int status;
    private String message;

    public TransactionException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
