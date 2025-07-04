package com.fawry;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        if (product != null && quantity > 0) {
            this.products.put(product.name, product);
        } else {
             throw new IllegalArgumentException("Product cannot be null and quantity must be greater than zero.");
        }
    }
    public Product getProduct(String name) {
        return this.products.get(name);
    }
    public void removeProduct(String name) {
        this.products.remove(name);
    }
    public Map<String, Product> getAllProducts() {
        return new HashMap<>(products);
    }
    public void clearInventory() {
        this.products.clear();
    }
    public void updateProductQuantity(String name, int newQuantity) {
        Product product = this.products.get(name);
        if (product != null && newQuantity >= 0) {
            product.quantity = newQuantity;
        } else {
            throw new IllegalArgumentException("Product not found or invalid quantity.");
        }
    }

}
