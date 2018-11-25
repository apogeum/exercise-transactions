package com.ripple.trustline;

public class TransactionParams {
    public Integer amount;
    public String from;
    public String to;

    public TransactionParams(Integer amount, String from, String to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public static TransactionParams build(String from, String to, Integer amount) {
        return new TransactionParams(amount, from, to);
    }

    @Override
    public String toString() {
        return "TransactionParams{" +
                "amount=" + amount +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
