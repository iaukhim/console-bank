package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.DebitCardDao;
import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.exceptions.internal.user.FillingUpLimitExceededError;
import com.unknown.bankapp.exceptions.internal.user.NotEnoughAtmBalance;
import com.unknown.bankapp.exceptions.internal.user.NotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class DebitCardServiceImpl implements DebitCardService{

    private final Long fillingUpLimit = 1_000_000L;

    @Autowired
    private DebitCardDao debitCardDao;

    @Autowired
    private AtmBalanceService atmBalanceService;

    @Override
    public Long showBalance(String cardNumber) {
        DebitCard debitCard = debitCardDao.loadByCardNumber(cardNumber);
        return debitCard.getBalance();
    }

    @Override
    public void fillUpTheCard(DebitCard debitCard, Long amount) {
        if (amount > fillingUpLimit){
            throw new FillingUpLimitExceededError();
        }
        DebitCard fullInfoCard = debitCardDao.loadByCardNumber(debitCard.getNumber());
        debitCardDao.changeBalance(fullInfoCard, fullInfoCard.getBalance() + amount);
        atmBalanceService.changeBalance(atmBalanceService.showBalance() + amount);
    }

    @Override
    public void withdrawMoney(DebitCard debitCard, Long amount) {
        Long atmBalance = atmBalanceService.showBalance();
        DebitCard fullInfoCard = debitCardDao.loadByCardNumber(debitCard.getNumber());
        if (amount > atmBalance){
            throw new NotEnoughAtmBalance(atmBalance);
        }
        if(amount > fullInfoCard.getBalance()){
             throw new NotEnoughMoneyException(fullInfoCard.getBalance());
        }
        debitCardDao.changeBalance(fullInfoCard, fullInfoCard.getBalance() - amount);
        atmBalanceService.changeBalance(atmBalance - amount);
    }

    @Override
    public Currency loadCurrency(String cardNumber) {
        return debitCardDao.loadByCardNumber(cardNumber).getCurrency();
    }
}
