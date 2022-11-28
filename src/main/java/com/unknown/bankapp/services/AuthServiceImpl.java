package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.DebitCardDao;
import com.unknown.bankapp.entities.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private DebitCardDao debitCardDao;

    @Override
    public boolean tryAuth(String cardNumber, String pinCode) {
        List<DebitCard> allDebitCards = debitCardDao.loadAll();
        Optional<DebitCard> first = allDebitCards.stream().filter(n -> n.getNumber().equals(cardNumber) && n.getPin().toString().equals(pinCode)).findFirst();
        if (first.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }

}
