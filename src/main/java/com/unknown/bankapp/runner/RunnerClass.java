package com.unknown.bankapp.runner;

import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.exceptions.internal.InternalException;
import com.unknown.bankapp.exceptions.user.CausedByUser;
import com.unknown.bankapp.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import com.unknown.bankapp.util.UtilClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Currency;

public class RunnerClass {
    @Autowired
    private UtilClass utilClass;
    @Autowired
    private DebitCardService debitCardService;
    private DebitCard currentCard;
    private BufferedReader br;
    public RunnerClass(UtilClass utilClass) {
        this.utilClass = utilClass;
    }
    public void run() {
        br = new BufferedReader(new InputStreamReader(System.in));
        Boolean isAuth = false;
        while (true) {
            try {
                if (!isAuth) {
                    System.out.println("Please, enter card number");
                    String cardNumber = br.readLine();
                    System.out.println("Please, enter pin-code");
                    String pinCode = br.readLine();
                    if (!utilClass.checkCardNumberFormat(cardNumber) || !utilClass.checkPinCodeFormat(pinCode)) {
                        System.out.println("Wrong card number or pin-code format");
                        continue;
                    }
                    isAuth = utilClass.authentication(cardNumber, pinCode);
                    if (isAuth) {
                        currentCard = new DebitCard(cardNumber, Long.parseLong(pinCode));
                    }
                    continue;
                }
                showMenu();
                String userInput = br.readLine();
                if (userInput.equals("0")) {
                    break;
                }
                if (!utilClass.checkUserMenuInput(userInput)) {
                    System.out.println("You should pick one of menu items listed above. Please, use correct digit to continue");
                    continue;
                }
                callService(userInput);
            } catch (CausedByUser e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IOException ex) {
                System.out.println("Check your input device");
            } catch (InternalException exc) {
                System.out.println("Sorry, atm not working properly right now. Please, try again later.\n " +
                                    "We would be grateful, if you will inform owner about this message\n");
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showMenu() {
        System.out.println("***********************************\n Hello, here's what you can do");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Fill up the card");
        System.out.println("0. Exit");
        System.out.println("***********************************\n");
    }

    private void callService(String userInput) throws IOException, NumberFormatException {
        switch (userInput) {
            case ("1"):
                Double balance = debitCardService.showBalance(currentCard.getNumber());
                Currency currency = debitCardService.loadCurrency(currentCard.getNumber());
                System.out.println("Your current balance is " + balance + " " + currency.getCurrencyCode() + "\n\n");
                break;
            case ("2"):
                System.out.println("Enter amount of money you want to withdraw");
                String userInputCase2 = br.readLine();
                if (!utilClass.checkUserAmountOfMoneyInput(userInputCase2)) {
                    System.out.println("Incorrect input");
                    break;
                }
                Double amountToWithdraw = Double.parseDouble(userInputCase2);
                debitCardService.withdrawMoney(currentCard, amountToWithdraw);
                System.out.println("Please, take your money \n \n");
                break;
            case ("3"):
                System.out.println("Enter amount of money you want to fill up");
                String userInputCase3 = br.readLine();
                if (!utilClass.checkUserAmountOfMoneyInput(userInputCase3)) {
                    System.out.println("Incorrect input");
                    break;
                }
                Double amountToFillUp = Double.parseDouble(userInputCase3);
                debitCardService.fillUpTheCard(currentCard, amountToFillUp);
        }
    }
}
