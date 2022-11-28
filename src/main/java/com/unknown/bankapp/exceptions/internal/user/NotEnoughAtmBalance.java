package com.unknown.bankapp.exceptions.internal.user;

public class NotEnoughAtmBalance extends CausedByUser{
    private Long atmBalance;

    public NotEnoughAtmBalance(Long atmBalance) {
        super("ATM does`nt have enough money. ATM balance is " + atmBalance);
    }

    public NotEnoughAtmBalance(String message) {
        super(message);
    }

    public NotEnoughAtmBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughAtmBalance(Throwable cause) {
        super(cause);
    }

    public NotEnoughAtmBalance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
