package com.unknown.bankapp.runner;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.unknown.bankapp.util.UtilClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@AllArgsConstructor
public class RunnerClass{

    @Autowired
    private UtilClass utilClass;
    private void showMenu(){
        System.out.println("***********************************\n Hello, here's what you can do");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw money");
        System.out.println("3. Fill up the card");
        System.out.println("0. Exit");
        System.out.println("***********************************");
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
                    System.out.println("Wrong card number of pin-code format");
                    continue;
                }
                isAuth = utilClass.authentication(cardNumber, pinCode);
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
            System.out.println("controller called");
        }
    }
}
