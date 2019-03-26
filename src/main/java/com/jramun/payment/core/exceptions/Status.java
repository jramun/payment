package com.jramun.payment.core.exceptions;

public interface Status {
    int EXCEPTION = 1;
    int EXIST_TRANSACTION = 2;
    int NOT_EXIST_TRANSACTION = 3;
    int VERIFY_TRANSACTION = 4;
    int DELETE_TRANSACTION = 5;
    int STATUS_TRANSACTION = 6;

}
