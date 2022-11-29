package com.unknown.bankapp.dao;

import java.util.Currency;

public interface AtmBalanceDao {

    Double loadBalance();

    Currency loadCurrency();

    void changeBalance(Double newBalance);
}
