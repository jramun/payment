package com.jramun.payment.modules.pay.exceptions.handlers;

import com.jramun.payment.core.services.interfaces.PaymentService;
import com.jramun.payment.modules.pay.exceptions.PayVerifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PayVerifyHandler {

    @Autowired
    private PaymentService paymentService;

    @ExceptionHandler(PayVerifyException.class)
    public ResponseEntity<?> verify(PayVerifyException e) {
        paymentService.failedTransaction(e.getToken(), e.getFactorNumber());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
