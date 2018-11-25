package com.ripple.trustline;

import java.util.UUID;

public class Transaction {
    public final String id;
    public final Dollars amount;

    public Transaction(String id, TransactionParams txParams) {
        this.id = id;
        this.amount = Dollars.amount(txParams.amount);
    }

    public Transaction(String id, String amount) {
        this.id = id;
        this.amount = Dollars.amount(Integer.parseInt(amount));
    }

    public Transaction(String id, Dollars amount) {
        this.id = id;
        this.amount = amount;
    }

    public static Transaction build(TransactionParams txParams) {
        return new Transaction(UUID.randomUUID().toString(), txParams);
    }

    public static Transaction build(String id, String amount) {
        return new Transaction(id, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Transaction reverse() {
        return new Transaction(this.id, Dollars.amount(-this.amount.amount));
    }

    public Integer amountInt() {
        return amount.amount;
    }

    public String logLine(){
        return this.id + " " + this.amount;
    }
}
