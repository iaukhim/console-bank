package com.unknown.bankapp.runner;

import com.unknown.bankapp.entities.DebitCard;
import com.unknown.bankapp.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import com.unknown.bankapp.util.UtilClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Currency;

public class RunnerClass{

    @Autowired
    private UtilClass utilClass;

    @Autowired
    private DebitCardService debitCardService;
    private DebitCard currentCard;

    public RunnerClass(UtilClass utilClass) {
        this.utilClass = utilClass;
    }

    public void run() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Boolean isAuth = false;
        while (true){
            if(!isAuth){
                System.out.println("Please, enter card number");
                String cardNumber = br.readLine();
                System.out.println("Please, enter pin-code");
                String pinCode = br.readLine();
                if (!utilClass.checkCardNumberFormat(cardNumber) || !utilClass.checkPinCodeFormat(pinCode)){
                    System.out.println("Wrong card number or pin-code format");
                    continue;
                }
                isAuth = utilClass.authentication(cardNumber, pinCode);
                if (isAuth){
                    currentCard = new DebitCard(cardNumber, Long.parseLong(pinCode));
                }
                continue;
            }
            showMenu();
            String userInput = br.readLine();
            if (userInput.equals("0")){
                break;
            }
            if(!utilClass.checkUserInput(userInput)){
                System.out.println("You should pick one of menu items listed above. Please, use correct digit to continue \n \n");
                continue;
            }
            callService(userInput);
        }
    }
    private void showMenu(){
        System.out.println("***********************************\n Hello, here's what you can do");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Fill up the card");
        System.out.println("0. Exit");
        System.out.println("***********************************");
    }

    private void callService(String userInput) {
        switch (userInput) {
            case ("1"):
                Long balance = debitCardService.showBalance(currentCard.getNumber());
                Currency currency = debitCardService.loadCurrency(currentCard.getNumber());
                System.out.println("Your current balance is " + balance + " " + currency.getCurrencyCode() + "\n\n");
                break;
        }
    }
}
