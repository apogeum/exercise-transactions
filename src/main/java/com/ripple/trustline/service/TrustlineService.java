package com.ripple.trustline.service;

import com.ripple.trustline.domain.Transaction;

import java.util.List;

public interface TrustlineService {


    void requestTransaction(Transaction tx);

    void commitTransaction(String txId);

    void rollbackTransaction(String txId);

    String name();

    Integer calculateBalance();


    List<Transaction> getCommittedReverse();

    void setCommitted(List<Transaction> s );

    boolean isTransactionCommitted(String id);
}
