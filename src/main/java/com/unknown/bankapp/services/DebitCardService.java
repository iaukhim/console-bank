package com.unknown.bankapp.services;

import com.unknown.bankapp.entities.DebitCard;

import java.util.Currency;

public interface DebitCardService {

    Long showBalance(String cardNumber);

    Currency loadCurrency(String cardNumber);

    void withdrawMoney(DebitCard debitCard, Long amount);

    void fillUpTheCard(DebitCard debitCard, Long amount);
}
