package com.ripple.trustline.dao;

public class TransactionParams {
    public String id;
    public Integer amount;
    public String from;
    public String to;

    public TransactionParams(String id, Integer amount, String from, String to) {
        this.id = id;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public static TransactionParams build(String id, String from, String to, Integer amount) {
        return new TransactionParams(id, amount, from, to);
    }

}
