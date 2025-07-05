package model;

import interfaces.Expirable;
import interfaces.Shippable;
import interfaces.ShippableItem;

import java.time.LocalDate;


public class Cheese extends Product implements Shippable, ShippableItem, Expirable {
    private double weight;
    private LocalDate expiryDate;

    public Cheese(String name, int qunatity, double price, double weight,LocalDate expiryDate) {
        super(name, qunatity, price);
        if(weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
        if(expiryDate == null)
            throw new IllegalArgumentException("Expiry date cannot be null");

        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }
    public String getName()
    {
        return super.getName();
    }

}
