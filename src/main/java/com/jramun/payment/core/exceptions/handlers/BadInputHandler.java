package com.jramun.payment.core.exceptions.handlers;

import com.jramun.payment.core.exceptions.BadInputException;
import com.jramun.payment.core.exceptions.TransactionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BadInputHandler {

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<?> badInput(BadInputException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> notFoundFactorNumber(TransactionException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
