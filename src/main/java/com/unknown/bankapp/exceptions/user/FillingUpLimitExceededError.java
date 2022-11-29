package com.unknown.bankapp.exceptions.user;

public class FillingUpLimitExceededError extends CausedByUser{
    public FillingUpLimitExceededError() {
        super("Limit for filling up the card is 1 000 000");
    }

    public FillingUpLimitExceededError(String message) {
        super(message);
    }

    public FillingUpLimitExceededError(String message, Throwable cause) {
        super(message, cause);
    }

    public FillingUpLimitExceededError(Throwable cause) {
        super(cause);
    }

    public FillingUpLimitExceededError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
