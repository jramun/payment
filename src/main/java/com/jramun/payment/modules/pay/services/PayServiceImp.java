package com.jramun.payment.modules.pay.services;

import com.jramun.payment.core.services.PaymentGateway;
import com.jramun.payment.core.services.interfaces.PaymentService;
import com.jramun.payment.modules.pay.clients.PayClient;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayServiceImp implements PayService {
    private PayClient payClient;
    private PaymentService paymentService;

    @Autowired
    public PayServiceImp(PayClient payClient, PaymentService paymentService) {
        this.payClient = payClient;
        this.paymentService = paymentService;
    }

    @Override
    public String send(double amount, String factorNumber, String mobile, String description)
            throws IOException, JSONException {
        String token = payClient.send(amount, factorNumber, description, mobile);
        paymentService.createTransaction(token, factorNumber, amount, description, mobile,
                PaymentGateway.PAY);
        return token;
    }

    @Override
    public void callBack(int status, String factorNumber, String token) {
        if (status == 1)
            paymentService.successTransaction(token, factorNumber);
        else
            paymentService.failedTransaction(token, factorNumber);
    }

    @Override
    public void verification(String token) throws IOException {
        payClient.verification(token);
    }
}
