package com.jramun.payment.modules.pay.clients;

import org.json.JSONException;

import java.io.IOException;

public interface PayClient {
    String send(double amount, String factorNumber, String description, String mobile)
            throws IOException, JSONException;

    void verification(String token, String factorNumber);
}
