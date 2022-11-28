package com.unknown.bankapp.dao;

import java.util.Currency;

public interface AtmBalanceDao {

    Long loadBalance();

    Currency loadCurrency();

    void changeBalance(Long newBalance);
}
