package com.fawry;

import java.util.HashMap;
import java.util.Map;





public class Cart {

    private double totalPrice;
    private int productCount;
    private Map<Product, Integer> products;
    private double totalWeight;
    
    public Cart() {
        this.totalPrice = 0.0;
        this.products =  new HashMap<>();
        this.productCount = 0;
    }

   
    public void addProduct(Product product, int userQuantity) throws QuantityExceededException {
      
        
        int availableQuantity = product.quantity;

        try {

            if (userQuantity > availableQuantity) {
                throw new QuantityExceededException("You cannot add more than the available quantity!");
            }
            else if (userQuantity <= 0) {
                throw new QuantityExceededException("Quantity must be greater than zero!");
            }
            else {
                this.products.put(product, userQuantity);
                this.totalPrice += product.price * userQuantity;
                this.productCount += userQuantity;
                this.totalWeight += product.weight * userQuantity;
                System.out.println("Successfully added " + userQuantity + " of " + product.name + " to the cart.");

            }
          

        } catch (QuantityExceededException e) {
            // special exception was needed to indetify the exact error of exceeding the quantity
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
        }

  
    }

    public void removeProduct(Product product) {
        if (this.products.containsKey(product)) {
            int quantity = this.products.get(product);
            this.totalPrice -= product.price * quantity;
            this.productCount -= quantity;
            this.totalWeight -= product.weight * quantity;
            this.products.remove(product);
            System.out.println("Successfully removed " + product.name + " from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public void updateProductQuantity(Product product, int newQuantity) throws QuantityExceededException {
        if (this.products.containsKey(product)) {
            int availableQuantity = product.quantity;
            if (newQuantity > availableQuantity) {
                throw new QuantityExceededException("You cannot update to more than the available quantity!");
            } else if (newQuantity <= 0) {
                throw new QuantityExceededException("Quantity must be greater than zero!");
            } else {
                int oldQuantity = this.products.get(product);
                
                this.totalPrice -= oldQuantity * product.price;
                this.productCount -= oldQuantity;
                this.totalWeight -= oldQuantity * product.weight;
                // Update the product quantity in the cart
                this.totalPrice += newQuantity  * product.price;
                this.productCount += newQuantity ;
                this.totalWeight += newQuantity  * product.weight;

                this.products.put(product, newQuantity);
                System.out.println("Successfully updated " + product.name + " quantity to " + newQuantity + ".");
            }
        } else {
            System.out.println("Product not found in the cart.");
        }
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }


    public Map<Product, Integer> getProducts() {
        return new HashMap<>(this.products);
    }

    public int getProductCount() {
        return this.productCount;
    }

    public double getTotalWeight() {

    return this.totalWeight; // Return total weight in grams
}

    public double getShiping() {
    double shippingCost = 0.0;
    if (getTotalWeight() > 5.0 ) {
        shippingCost = 30.0; // Flat rate for heavy packages
    } else {
        shippingCost = 0.0; // Flat rate for light packages
    }
    return shippingCost;
}

    public void clearCart() {
        this.products.clear();
        this.totalPrice = 0.0;
        this.productCount = 0;
        this.totalWeight = 0.0;
    }

}
