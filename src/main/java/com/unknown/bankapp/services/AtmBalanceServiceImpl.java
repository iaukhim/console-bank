package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.AtmBalanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class AtmBalanceServiceImpl implements AtmBalanceService {

    @Autowired
    private AtmBalanceDao atmBalanceDao;

    @Override
    public Double showBalance() {
        return atmBalanceDao.loadBalance();
    }

    @Override
    public void changeBalance(Double newBalance) {
        atmBalanceDao.changeBalance(newBalance);
    }

    @Override
    public Currency loadCurrency() {
        return atmBalanceDao.loadCurrency();
    }
}
