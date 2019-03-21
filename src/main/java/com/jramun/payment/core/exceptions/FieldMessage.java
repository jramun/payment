package com.jramun.payment.core.exceptions;

public class FieldMessage {

    private String name;
    private Object value;
    private String message;
    private String code;
    private String[] codes;

    public FieldMessage(String name, Object value, String message, String code, String[] codes) {
        this.name = name;
        this.value = value;
        this.message = message;
        this.code = code;
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public FieldMessage setName(String name) {
        this.name = name;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public FieldMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}
