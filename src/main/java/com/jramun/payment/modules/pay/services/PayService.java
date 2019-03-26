package com.jramun.payment.modules.pay.services;

import org.json.JSONException;

import java.io.IOException;

public interface PayService {

    String send(double amount, String factorNumber, String mobile, String description) throws IOException, JSONException;

    String callBack(int status, String factorNumber, String token);

    void cancel(String token, String factorNumber);
}
