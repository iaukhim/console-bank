package com.unknown.bankapp.dao;

import com.unknown.bankapp.entities.DebitCard;
import java.util.List;

public interface DebitCardDaoInterface {
    List<DebitCard> loadAll();
}
