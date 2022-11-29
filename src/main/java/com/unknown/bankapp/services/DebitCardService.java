package com.unknown.bankapp.services;

import com.unknown.bankapp.entities.DebitCard;

import java.util.Currency;

public interface DebitCardService {

    Double showBalance(String cardNumber);

    Currency loadCurrency(String cardNumber);

    void withdrawMoney(DebitCard debitCard, Double amount);

    void topUpCard(DebitCard debitCard, Double amount);
}
