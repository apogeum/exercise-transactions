package com.ripple.trustline;

public interface TrustlineService {


    void requestTransaction(Transaction tx);

    void commitTransaction(String txId);

    void rollbackTransaction(String txId);

    String name();

    Integer calculateBalance();

}
