package com.ripple.trustline.domain;

public class Dollar {
    public final Integer amount;

    public Dollar(Integer amount) {
        this.amount = amount;
    }

    public Dollar(){
        this.amount = 0;
    }

    public static Dollar amount(Integer amount) {
        if (amount == null){
            throw new RuntimeException("amount cannot be null");
        }
        return new Dollar(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dollar dollars = (Dollar) o;

        return amount.equals(dollars.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}

