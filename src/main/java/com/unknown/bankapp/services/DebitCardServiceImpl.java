package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.DebitCardDao;
import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.exceptions.user.DepositLimitExceededError;
import com.unknown.bankapp.exceptions.user.NotEnoughAtmBalance;
import com.unknown.bankapp.exceptions.user.NotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class DebitCardServiceImpl implements DebitCardService{

    private final Long FILING_UP_LIMIT = 1_000_000L;

    @Autowired
    private DebitCardDao debitCardDao;

    @Autowired
    private AtmBalanceService atmBalanceService;

    @Override
    public Double showBalance(String cardNumber) {
        DebitCard debitCard = debitCardDao.loadByCardNumber(cardNumber);
        return debitCard.getBalance();
    }

    @Override
    public void topUpCard(DebitCard debitCard, Double amount) throws DepositLimitExceededError {
        if (amount > FILING_UP_LIMIT){
            throw new DepositLimitExceededError();
        }
        DebitCard fullInfoCard = debitCardDao.loadByCardNumber(debitCard.getNumber());
        debitCardDao.changeBalance(fullInfoCard, fullInfoCard.getBalance() + amount);
        atmBalanceService.changeBalance(atmBalanceService.showBalance() + amount);
    }

    @Override
    public void withdrawMoney(DebitCard debitCard, Double amount) throws NotEnoughAtmBalance, NotEnoughMoneyException {
        Double atmBalance = atmBalanceService.showBalance();
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
