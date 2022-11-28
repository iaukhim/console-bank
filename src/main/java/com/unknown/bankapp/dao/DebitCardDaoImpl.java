package com.unknown.bankapp.dao;

import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.exceptions.internal.DaoLayerException;
import com.unknown.bankapp.exceptions.internal.user.NoSuchCardException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Repository
public class DebitCardDaoImpl implements DebitCardDao {

    private final String pathToFile = "src/main/resources/db/DebitCards.csv";
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public void changeBalance(DebitCard debitCard, Long newBalance) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
            String cardStringFormat = lines.get(debitCard.getId().intValue() - 1);
            List<String> parsedCardInfo = new ArrayList<>(Arrays.stream(cardStringFormat.split(" ")).toList());
            parsedCardInfo.set(4, newBalance.toString());
            StringBuffer stringBuffer = new StringBuffer();
            for (String element: parsedCardInfo) {
                stringBuffer.append(element);
                stringBuffer.append(" ");
            }
            String correctedCardInfo = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
            lines.set(debitCard.getId().intValue() - 1, correctedCardInfo);
            Files.write(Paths.get(pathToFile), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE);

        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }

    }

    public List<DebitCard> loadAll() {
        List<DebitCard> cards = new ArrayList<>();
        try (FileReader fileReader = new FileReader(pathToFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (bufferedReader.ready()) {
                List<String> cardInfo = Arrays.stream(bufferedReader.readLine().split("\s")).toList();
                cards.add(constructCardFromInfo(cardInfo));
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }
        return cards;

    }

    @Override
    public DebitCard loadByCardNumber(String cardNumber) {
        try (FileReader fileReader = new FileReader(pathToFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (bufferedReader.ready()) {
                List<String> cardInfo = Arrays.stream(bufferedReader.readLine().split("\s")).toList();
                DebitCard debitCard = constructCardFromInfo(cardInfo);
                if (debitCard.getNumber().equals(cardNumber)) {
                    return debitCard;
                }
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }
        throw new NoSuchCardException();
    }

    private DebitCard constructCardFromInfo(List<String> cardInfo) {
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
