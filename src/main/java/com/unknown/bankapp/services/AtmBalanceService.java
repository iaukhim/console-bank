package com.unknown.bankapp.services;

import java.util.Currency;

public interface AtmBalanceService {
    Double showBalance();

    Currency loadCurrency();

    void changeBalance(Double newBalance);
}
