package com.jramun.payment.modules.pay.restcontrollers;

import com.jramun.payment.core.exceptions.BadInputException;
import com.jramun.payment.core.exceptions.Status;
import com.jramun.payment.modules.pay.models.PayRequest;
import com.jramun.payment.modules.pay.models.PayResponse;
import com.jramun.payment.modules.pay.services.PayService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/pay")
public class PayRestController {

    @Value("${pay.server.redirect}")
    private String redirectUrl;
    private PayService payService;

    @Autowired
    public PayRestController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/send")
    public ResponseEntity<PayResponse> send(@Valid @RequestBody PayRequest request, BindingResult result)
            throws IOException, JSONException {
        if (result.hasErrors())
            throw new BadInputException(result.toString(),result.getFieldErrors());


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
                                           @RequestParam(value = "factor_number") String factorNumber,
                                           @RequestParam(value = "token") String token) {
        String result = payService.callBack(transactionStatus, factorNumber, token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestParam("factor_number") String factorNumber,
                                         @RequestParam("token") String token) {
        payService.cancel(token, factorNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
