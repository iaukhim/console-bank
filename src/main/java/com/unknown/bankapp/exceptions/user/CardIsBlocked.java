package com.unknown.bankapp.exceptions.user;

import java.time.LocalDateTime;

public class CardIsBlocked extends CausedByUser{
    public CardIsBlocked() {
    }

    public CardIsBlocked(LocalDateTime dateOfBlock) {
        super("Your card was blocked " + dateOfBlock.toString() + ". It will be automatically unblocked within 24 hours.");
    }

    public CardIsBlocked(String message, Throwable cause) {
        super(message, cause);
    }

    public CardIsBlocked(Throwable cause) {
        super(cause);
    }

    public CardIsBlocked(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
