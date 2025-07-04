package com.fawry;


public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    
    public Customer(String name, double balance) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    // Setters
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    
    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deduct cannot be negative.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Not enough balance.");
        }
        this.balance -= amount;
    }
}


