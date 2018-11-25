package com.ripple.trustline;

public class Dollars {
    public final Integer amount;

    private Dollars(Integer amount) {
        this.amount = amount;
    }

    public static Dollars amount(Integer amount) {
        if (amount == null){
            throw new RuntimeException("amount cannot be null");
        }
        return new Dollars(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dollars dollars = (Dollars) o;

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

