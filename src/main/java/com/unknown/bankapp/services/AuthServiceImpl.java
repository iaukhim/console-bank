package com.unknown.bankapp.services;

import com.unknown.bankapp.dao.DebitCardDao;
import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.exceptions.user.CardIsBlocked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private int attempts;
    @Autowired
    private DebitCardDao debitCardDao;

    @Override
    public boolean tryAuth(String cardNumber, String pinCode) throws CardIsBlocked{
        DebitCard fullInfoCard = debitCardDao.loadByCardNumber(cardNumber);
        if(fullInfoCard.isBlocked()){
            if(ChronoUnit.MINUTES.between(fullInfoCard.getDateOfBlock(), LocalDateTime.now()) < 1440) {
                throw new CardIsBlocked(fullInfoCard.getDateOfBlock());
            }
            else {
                debitCardDao.setBlockStatus(cardNumber, false);
            }
        }
        List<DebitCard> allDebitCards = debitCardDao.loadAll();
        Optional<DebitCard> first = allDebitCards.stream().filter(n -> n.getNumber().equals(cardNumber) && n.getPin()== Long.parseLong(pinCode)).findFirst();
        if (first.isPresent()) {
            attempts = 0;
            return true;
        }
        attempts++;
        if (attempts == 3){
            debitCardDao.setBlockStatus(cardNumber, true);
            attempts = 0;
        }
        return false;
    }

}
