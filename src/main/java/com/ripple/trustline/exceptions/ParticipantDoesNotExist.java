package com.ripple.trustline.exceptions;

public class ParticipantDoesNotExist extends RuntimeException {
    public ParticipantDoesNotExist(String from) {
        super(from);
    }
}
