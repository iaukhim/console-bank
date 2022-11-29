package com.unknown.bankapp.exceptions.user;

public class NotEnoughMoneyException extends CausedByUser {

    private Double balance;

    public NotEnoughMoneyException(Double balance) {
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
