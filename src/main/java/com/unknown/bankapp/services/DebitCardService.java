package com.unknown.bankapp.services;

import java.util.Currency;

public interface DebitCardService {

    Long showBalance(String cardNumber);

    Currency loadCurrency(String cardNumber);
}
