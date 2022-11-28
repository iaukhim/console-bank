package com.unknown.bankapp.exceptions.internal.user;

public abstract class CausedByUser extends RuntimeException {
    public CausedByUser() {
    }

    public CausedByUser(String message) {
        super(message);
    }

    public CausedByUser(String message, Throwable cause) {
        super(message, cause);
    }

    public CausedByUser(Throwable cause) {
        super(cause);
    }

    public CausedByUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
