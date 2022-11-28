package com.unknown.bankapp.entities;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Currency;

@NoArgsConstructor
@ToString(callSuper = true)
public class DebitCard extends PlasticCard{

    private Boolean isOverdraftAvailable;

    private Long overdraftLimit;

    public DebitCard(Long id, String number, Long pin, LocalDate expDate, Long balance, Currency currency, Boolean isOverdraftAvailable, Long overdraftLimit) {
        super(id, number, pin, expDate, balance, currency);
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = overdraftLimit;
    }

    public DebitCard(Boolean isOverdraftAvailable, Long overdraftLimit) {
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = overdraftLimit;
    }

    public DebitCard(String number, Long pin) {
        super(number, pin);
    }
}
