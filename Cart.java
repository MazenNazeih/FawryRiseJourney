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
    // double kg = this.totalWeight / 1000.0;
    // kg = Math.round(kg * 100.0) / 100.0;
    // return kg;
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

}
