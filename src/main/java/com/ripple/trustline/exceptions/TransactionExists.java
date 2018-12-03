package com.ripple.trustline.exceptions;

public class TransactionExists extends RuntimeException {
    public TransactionExists(String id) {
        super(id);
    }
}
