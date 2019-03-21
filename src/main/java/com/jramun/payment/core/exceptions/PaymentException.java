package com.jramun.payment.core.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentException extends RuntimeException {
    private Logger logger = Logger.getLogger(PaymentException.class.getSimpleName());

    private Date date;
    private int status;
    private String message;
    private List<FieldMessage> fieldMessages;

    public PaymentException(int status, String message) {
        super(message);
        this.message = message;
        this.date = new Date();
        this.status = status;
        fieldMessages = new ArrayList<>();
        logger.log(Level.WARNING, message);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public void setFieldMessages(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }
}
