package com.unknown.bankapp.dao;

import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.internal.DaoLayerException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class DebitCardDao implements DebitCardDaoInterface{

    public List<DebitCard> loadAll() {
        List<DebitCard> cards = new ArrayList<>();
        try (FileReader fileReader = new FileReader("src/main/resources/db/DebitCards.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader);
            )
        {
            while (bufferedReader.ready()){
                List<String> cardInfo = Arrays.stream(bufferedReader.readLine().split("\s")).toList();
                cards.add(constructCardFromInfo(cardInfo));
            }
        } catch (IOException e) {
            throw new DaoLayerException(e);
        }
        return cards;

    }

    private DebitCard constructCardFromInfo(List<String> cardInfo){
        Long id = Long.parseLong(cardInfo.get(0));
        String cardNumber = cardInfo.get(1);
        Long pin = Long.parseLong(cardInfo.get(2));
        String[] split = cardInfo.get(3).split("\\D");
        LocalDate expDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        Long balance = Long.parseLong(cardInfo.get(4));
        Currency currency = Currency.getInstance(cardInfo.get(5));
        Boolean isOverdraftAvailable = Boolean.getBoolean(cardInfo.get(6));
        Long overDraftLimit = Long.parseLong(cardInfo.get(7));

        return new DebitCard(id, cardNumber, pin, expDate, balance, currency, isOverdraftAvailable, overDraftLimit);
    }
}
