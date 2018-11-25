package com.ripple.trustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class LocalTrustlineService implements TrustlineService {

    Logger logger = LoggerFactory.getLogger(LocalTrustlineService.class);

    private final Map<String, Transaction> requested;
    private final List<Transaction> committed;

    @Value("${trustline.account}")
    private String name;

    private FileBasedTransactionsLog log;


    @Autowired
    public LocalTrustlineService(FileBasedTransactionsLog l){
        this.requested = new HashMap<>();
        this.log = l;
        this.committed = log.loadTransactions();
    }

    // this is only for testing purposes
    public LocalTrustlineService(String name){
        this.requested = new HashMap<>();
        this.committed = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void requestTransaction(@NotNull Transaction tx) {
        logger.info("requestTransaction " + tx);
        requested.put(tx.id, tx);
    }

    @Override
    public void commitTransaction(@NotNull String txId) {
        logger.info("commitTransaction " + txId);

        Transaction tx = requested.remove(txId);
        if (tx == null){
            throw new RuntimeException(String.format("Transaction [%s] not present in REQUESTED log", txId));
        }
        appendToLogIfPresent(tx);
        committed.add(tx);

        logger.info("balance updated by: " + tx.amount);
        logger.info("current balance:" + calculateBalance());
    }

    private void appendToLogIfPresent(Transaction tx) {
        if (log != null) {
            log.appendTransaction(tx);
        } else {
            logger.warn("Transaction log not configured properly. Transaction information resides only in memory.");
        }
    }

    public Integer calculateBalance() {
        return committed.stream().mapToInt(Transaction::amountInt).sum();
    }

    @Override
    public void rollbackTransaction(@NotNull String txId) {
        logger.info("rollbackTransaction " + txId);
        Transaction tx = requested.remove(txId);
    }

    @Override
    public String name() {
        return name;
    }


}
