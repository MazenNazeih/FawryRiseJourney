package com.fawry;

import java.time.LocalDate;


public  class Product  implements Shippable {
    public String name;
    public double price;
    public int quantity;
    public Boolean canExpire;
    public double weight;
    public Boolean shippable;
    public LocalDate expirationDate;

    public Product(String name, double price, int quantity, Boolean canExpire, double weight, LocalDate expirationDate) throws IllegalArgumentException {

        if (name == null || price <= 0 || quantity <= 0 || weight <= 0 || canExpire == null || !(canExpire instanceof Boolean)) {
            throw new IllegalArgumentException("Name, price, quantity,weight, canExpire must be valid.");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
       
        if (canExpire && expirationDate != null && expirationDate.isAfter(LocalDate.now()) ) {
            this.canExpire = canExpire;
            this.expirationDate = expirationDate;
        }
        else if (canExpire && expirationDate != null && expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past for products that can expire.");
        }
        else if (canExpire && expirationDate == null) {
            throw new IllegalArgumentException("Expiration date must be provided for products that can expire.");
        }
        else if (!canExpire && expirationDate != null) {
            throw new IllegalArgumentException("Expiration date should not be provided for products that do not expire.");
        }
        else if (!canExpire && expirationDate == null) {
            this.canExpire = false;
            this.expirationDate = null;
        }
        else {
            throw new IllegalArgumentException("Invalid canExpire value or expiration date.");
        }

        this.weight = weight;
        if (weight >= 5){
             this.shippable = true;
        }
        else {
            this.shippable = false;
        }
        
    }

     public Product(String name, double price, int quantity, Boolean canExpire, double weight) {
        this(name, price, quantity, canExpire, weight, null);
     }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }

}
