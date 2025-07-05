package model;

import interfaces.Shippable;
import interfaces.ShippableItem;

public class TV extends Product implements Shippable, ShippableItem {
    private double weight;


    public TV(String name, int qunatity, double price, double weight) {
        super(name, qunatity, price);
        if(weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");

        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }


    public String getName()
    {
        return super.getName();
    }

}
