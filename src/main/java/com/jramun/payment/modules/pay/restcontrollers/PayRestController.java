package com.jramun.payment.modules.pay.restcontrollers;

import com.jramun.payment.modules.pay.models.PayRequest;
import com.jramun.payment.modules.pay.models.PayResponse;
import com.jramun.payment.modules.pay.services.PayService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/pay")
public class PayRestController {

    @Value("${pay.redirect}")
    private String redirectUrl;
    private PayService payService;
    private Logger logger = Logger.getLogger(PayRestController.class.getName());

    @Autowired
    public PayRestController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/send")
    public ResponseEntity<PayResponse> send(@RequestBody PayRequest request)
            throws IOException, JSONException {
        double amount = request.getAmount();
        String mobile = request.getMobile();
        String description = request.getDescription();
        String factorNumber = request.getFactorNumber();
        String token = payService.send(amount, factorNumber, mobile, description);
        return new ResponseEntity<>(new PayResponse().
                initiate(amount, factorNumber, mobile, token, redirectUrl + factorNumber,
                        description), HttpStatus.OK);
    }

    @GetMapping("/call-back")
    public ResponseEntity<String> callBack(@RequestParam(value = "status") int transactionStatus,
                                           @RequestParam(value = "factorNumber") String factorNumber,
                                           @RequestParam(value = "token") String token) {
        payService.callBack(transactionStatus, factorNumber, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("token") String token) throws IOException {
        payService.verification(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
