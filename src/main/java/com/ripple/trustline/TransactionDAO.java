package com.ripple.trustline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDAO implements Serializable {
    public final String id;
    public final String amount;

    public TransactionDAO(String id, String amount) {

        this.id = id;
        this.amount = amount;
    }

    public static TransactionDAO build(Transaction tx) {
        return new TransactionDAO(tx.id, tx.amount.toString());
    }

    public Transaction transaction() {
        return Transaction.build(id, amount);
    }
}
