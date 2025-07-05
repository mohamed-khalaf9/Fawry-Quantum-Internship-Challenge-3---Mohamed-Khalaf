package model;

import interfaces.Expirable;

import java.time.LocalDate;

public class Biscuits extends Product implements Expirable {
    private LocalDate expiryDate;

    public Biscuits(String name, int qunatity, double price,LocalDate expiryDate) {
        super(name, qunatity, price);
        if(expiryDate == null)
            throw new IllegalArgumentException("Expiry date cannot be null");

        this.expiryDate = expiryDate;

    }



    @Override
    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }


}

