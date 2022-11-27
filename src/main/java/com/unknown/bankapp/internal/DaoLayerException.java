package com.unknown.bankapp.internal;

public class DaoLayerException extends InternalException{

    public DaoLayerException(Throwable cause) {
        super(cause);
    }

    private final String messageForUser = "Sorry, something went wrong on our side. Please try again later";
}
