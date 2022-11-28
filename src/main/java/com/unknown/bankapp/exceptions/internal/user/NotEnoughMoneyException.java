package com.unknown.bankapp.exceptions.internal.user;

public class NotEnoughMoneyException extends CausedByUser {

    private Long balance;

    public NotEnoughMoneyException(Long balance) {
        super("You don`t have enough money on your card. Your balance is " + balance);
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyException(Throwable cause) {
        super(cause);
    }

    public NotEnoughMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
