package com.unknown.bankapp.exceptions.internal;

public abstract class InternalException extends RuntimeException{

    private final String messageForUser = "Sorry, something went wrong on our side. Please try again later";
    public InternalException(Throwable cause) {
        super(cause);
    }
}
