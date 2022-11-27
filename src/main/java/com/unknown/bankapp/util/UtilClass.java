package com.unknown.bankapp.util;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.unknown.bankapp.services.AuthServiceInterface;

import java.util.Arrays;

@AllArgsConstructor
public class UtilClass{

    @Autowired
    private AuthServiceInterface authService;

    public   boolean checkUserInput(String input){
        try {
            Long menuItem = Long.parseLong(input);
            if (menuItem <1 || menuItem>3){
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public   boolean authentication(String cardNumber, String pinCode){
        boolean authResult = authService.tryAuth(cardNumber, pinCode);
        if (!authResult){
            System.out.println("Auth error. Check card number or pin-code");
        }
        return authResult;
    }

    public   boolean checkPinCodeFormat(String pinCode){
        if(pinCode.trim().length() != 4){
            return false;
        }
        try {
            Long menuItem = Long.parseLong(pinCode);
            if (menuItem <0 || menuItem>9999){
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public   boolean checkCardNumberFormat(String cardNumber){
        if(cardNumber.length() != 19 || cardNumber.trim().length() != 19 || cardNumber.trim().split("-").length != 4){
            return false;
        }
        try {
            long count = Arrays.stream(cardNumber.trim().split("-")).map((n) -> {
                String trimmedString = n.trim();
                return Long.parseLong(trimmedString);
            }).filter(n -> {
                if (n < 0 || n > 9999) {
                    return false;
                }
                return true;
            }).count();
            if(count !=4){
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}