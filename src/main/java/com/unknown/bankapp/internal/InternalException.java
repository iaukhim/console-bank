package com.unknown.bankapp.internal;

public abstract class InternalException extends RuntimeException{
    public InternalException(Throwable cause) {
        super(cause);
    }
}
