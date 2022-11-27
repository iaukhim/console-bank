package com.unknown.bankapp.services;

public interface AuthServiceInterface {

     boolean tryAuth(String cardNumber, String pinCode);
}
