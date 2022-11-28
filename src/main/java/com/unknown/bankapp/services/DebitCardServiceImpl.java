package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.DebitCardDao;
import com.unknown.bankapp.entities.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class DebitCardServiceImpl implements DebitCardService{

    @Autowired
    private DebitCardDao debitCardDao;

    @Override
    public Long showBalance(String cardNumber) {
        DebitCard debitCard = debitCardDao.loadByCardNumber(cardNumber);
        return debitCard.getBalance();
    }

    @Override
    public Currency loadCurrency(String cardNumber) {
        return debitCardDao.loadByCardNumber(cardNumber).getCurrency();
    }
}
