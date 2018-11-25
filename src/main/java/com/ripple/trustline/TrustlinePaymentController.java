package com.ripple.trustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trustline/payment")

public class TrustlinePaymentController {

    Logger logger = LoggerFactory.getLogger(TrustlinePaymentController.class);

    @Autowired
    private TrustlineCoordinator coordinator;

    @PostMapping
    public @ResponseBody
    void requestPayment(@RequestBody TransactionParams txParams){
        coordinator.post(txParams);
    }



}
