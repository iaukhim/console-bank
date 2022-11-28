package com.unknown.bankapp.dao;

import com.unknown.bankapp.entities.DebitCard;
import java.util.List;

public interface DebitCardDao {
    List<DebitCard> loadAll();

    DebitCard loadByCardNumber(String cardNumber);
}
