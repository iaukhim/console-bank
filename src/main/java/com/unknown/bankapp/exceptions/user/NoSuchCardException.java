package com.unknown.bankapp.exceptions.user;

public class NoSuchCardException extends CausedByUser{
    public NoSuchCardException() {
        super("There is no card with such number");
    }

    public NoSuchCardException(String message) {
        super(message);
    }

    public NoSuchCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCardException(Throwable cause) {
        super(cause);
    }

    public NoSuchCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
