package com.unknown.bankapp.exceptions.user;

public class DepositLimitExceededError extends CausedByUser{
    public DepositLimitExceededError() {
        super("Limit for deposing account is 1 000 000");
    }

    public DepositLimitExceededError(String message) {
        super(message);
    }

    public DepositLimitExceededError(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositLimitExceededError(Throwable cause) {
        super(cause);
    }

    public DepositLimitExceededError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
