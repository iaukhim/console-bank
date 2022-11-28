package com.unknown.bankapp.services;

import java.util.Currency;

public interface AtmBalanceService {
    Long showBalance();

    Currency loadCurrency();

    void changeBalance(Long newBalance);
}
