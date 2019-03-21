package com.jramun.payment.modules.pay.clients;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

@Component
public class PayClientImp implements PayClient {

    @Value("${pay.token}")
    private String token;

    @Value("${pay.api}")
    private String sendUrl;

    @Value("${pay.verify}")
    private String verifyUrl;

    @Value("${pay.redirect}")
    private String redirect;

    @Override
    public String send(double amount, String factorNumber, String description, String mobile)
            throws IOException, JSONException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("api", token));
        params.add(new BasicNameValuePair("redirect", redirect + factorNumber));
        params.add(new BasicNameValuePair("amount", amount + ""));
        params.add(new BasicNameValuePair("mobile", mobile));
        params.add(new BasicNameValuePair("factorNumber", factorNumber));
        params.add(new BasicNameValuePair("description", description));
        HttpResponse response = http("POST", sendUrl, params);
        String responseJson = new BasicResponseHandler().handleResponse(response);
        return new JSONObject(responseJson).getString("token");
    }

    @Override
    public void verification(String token) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("api", this.token));
        params.add(new BasicNameValuePair("token", token));
        HttpResponse response = http("POST", verifyUrl, params);
        new BasicResponseHandler().handleResponse(response);
    }


    private HttpResponse http(String method, String url, List<NameValuePair> params)
            throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(USER_AGENT, USER_AGENT);
        assert url != null;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty(USER_AGENT, USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        return httpClient.execute(httpPost);

    }
}
