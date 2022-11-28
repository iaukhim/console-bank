package com.unknown.bankapp.entities;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@NoArgsConstructor
@ToString(callSuper = true)
public class DebitCard extends PlasticCard{

    private Boolean isOverdraftAvailable;

    private Long overdraftLimit;

    public DebitCard(Long id, String number, Long pin, LocalDate expDate, Long balance, Currency currency, Boolean isOverdraftAvailable, Long overdraftLimit, boolean isBlocked, LocalDateTime dateOfBlock) {
        super(id, number, pin, expDate, balance, currency, isBlocked, dateOfBlock);
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = overdraftLimit;
    }

    public DebitCard(Boolean isOverdraftAvailable, Long overdraftLimit) {
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = overdraftLimit;
    }

    public DebitCard(String number, Long pin, Boolean isOverdraftAvailable, Long overdraftLimit) {
        super(number, pin);
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = overdraftLimit;
    }

    public DebitCard(String number, Long pin) {
        super(number, pin);
    }
}
