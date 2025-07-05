package model;

public class Customer {
    private String name;
    private double currentBalance;

    public Customer(double currentBalance, String name) {
        if(currentBalance < 0)
            throw new IllegalArgumentException("Invalid balance");
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid name");

        this.currentBalance = currentBalance;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public double getCurrentBalance() {
        return this.currentBalance;
    }
    public void withdraw(double amount) {
        if(amount < 0)
            throw new IllegalArgumentException("Invalid amount");
        if(amount > currentBalance)
            throw new IllegalArgumentException("Invalid amount");

        this.currentBalance -= amount;
    }
}

