package com.jramun.payment.core.exceptions;

import org.springframework.validation.FieldError;

import java.util.List;

public class BadInputException extends RuntimeException {

    private String message;
    private List<FieldError> fieldErrors;

    public BadInputException(String message, List<FieldError> fieldErrors) {
        super(message);
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
