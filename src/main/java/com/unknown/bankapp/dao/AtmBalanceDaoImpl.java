package com.unknown.bankapp.dao;

import com.unknown.bankapp.exceptions.internal.DaoLayerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Repository
public class AtmBalanceDaoImpl implements AtmBalanceDao {

    private final String PATH_TO_FILE = "src/main/resources/db/atmBalance.csv";

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public void changeBalance(Double newBalance) {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (bufferedReader.ready()) {
                List<String> atmBalance = new ArrayList(Arrays.stream(bufferedReader.readLine().split(" ")).toList());
                atmBalance.set(0, newBalance.toString());
                StringBuffer stringBuffer = new StringBuffer();
                atmBalance.stream().forEach(n -> {
                    stringBuffer.append(n);
                    stringBuffer.append(" ");
                });
                Files.writeString(Paths.get(PATH_TO_FILE), stringBuffer.toString());
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }
    }

    @Override
    public Currency loadCurrency() {
        String currencyCode = null;
        try (FileReader fileReader = new FileReader(PATH_TO_FILE);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (bufferedReader.ready()) {
                currencyCode = Arrays.stream(bufferedReader.readLine().split(" ")).toList().get(1);

            }
        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }
        return Currency.getInstance(currencyCode);
    }

    @Override
    public Double loadBalance() {
        Double atmBalance = 0D;
        try (FileReader fileReader = new FileReader(PATH_TO_FILE);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (bufferedReader.ready()) {
                String stringBalance = Arrays.stream(bufferedReader.readLine().split(" ")).toList().get(0);
                atmBalance = Double.parseDouble(stringBalance);
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new DaoLayerException(e);
        }
        return atmBalance;
    }
}
