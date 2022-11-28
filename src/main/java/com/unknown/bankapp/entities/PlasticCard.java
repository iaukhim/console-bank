package com.unknown.bankapp.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.Currency;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public abstract class PlasticCard {

    private Long id;

    private String number;

    private Long pin;

    private LocalDate expDate;

    private Long balance;

    private Currency currency;

    public PlasticCard(String number, Long pin) {
        this.number = number;
        this.pin = pin;
    }
}
