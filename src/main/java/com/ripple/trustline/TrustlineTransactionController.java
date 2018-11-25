package com.ripple.trustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/trustline/transaction")
public class TrustlineTransactionController {

    Logger logger = LoggerFactory.getLogger(TrustlineTransactionController.class);



    @Autowired
    private LocalTrustlineService svc;

    @Autowired
    private TrustlineCoordinator coordinator;

    @GetMapping
    public String balance() {
        return String.valueOf(svc.calculateBalance());
    }

    @PostMapping
    public void requestTransaction(@RequestBody @NotNull TransactionDAO tx){
        // TODO validate
        svc.requestTransaction(tx.transaction());
    }

    @PutMapping
    public void commitTransaction(@RequestBody String txId){
        svc.commitTransaction(txId);
    }

    @DeleteMapping("/{txId}")
    public void rollbackTransaction(@PathVariable String txId){
        svc.rollbackTransaction(txId);
    }


}
