package com.ripple.trustline.controllers;

import com.ripple.trustline.service.LocalTrustlineService;
import com.ripple.trustline.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


// WARNING: this controller exposes capability of rewriting transaction history
// use with extreme caution

@RestController
@RequestMapping("/trustline/committed")
public class TrustlineCommittedController {

    Logger logger = LoggerFactory.getLogger(TrustlineCommittedController.class);



    @Autowired
    private LocalTrustlineService svc;

    @Autowired
    private TrustlineCoordinator coordinator;

    @GetMapping
    public List<Transaction> getCommitted() {
        return svc.getCommittedReverse();
    }

    @PostMapping
    public void SetCommitted(@RequestBody @NotNull List<Transaction> committed){
        svc.setCommitted(committed);
    }

}
