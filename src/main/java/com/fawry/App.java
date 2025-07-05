package com.fawry;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Inventory inventory = new Inventory();
        Product product1 = null;
        Product product2 = null;
        Product product3 = null;
        try {
            // Adding a product with valid parameters
            product1 = new Product("Chocolate", 2.50, 100, false, 0.2);
            product2 = new Product("Milk", 1.50, 200, true, 1.0, LocalDate.of(2026, 12, 31));
            product3 = new Product("Bread", 1.00, 50, true, 10, LocalDate.of(2027, 10, 15));
            inventory.addProduct(product1, 50);
            inventory.addProduct(product2, 30);
            inventory.addProduct(product3, 20);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        System.out.println("Inventory initialized with products:");
        inventory.getAllProducts().forEach((name, product) -> {
            System.out.println(name + ": " + product.price + " USD, Quantity: " + product.quantity);
        });


        Customer customer1 = null;
        try {
            // Creating a customer with valid parameters
            customer1 = new Customer("Alice", 100.0);
            System.out.println("Customer created: " + customer1.getName() + ", Balance: " + customer1.getBalance() + " USD");
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating customer: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating customer: " + e.getMessage());
        }

    
        try {
            // Adding products to the cart
            customer1.getCart().addProduct(product1, 2);
            customer1.getCart().addProduct(product2, 3);
            customer1.getCart().addProduct(product3, 1);
            System.out.println("Total price in cart: " + customer1.getCart().getTotalPrice() + " USD");
            System.out.println("Total product count in cart: " + customer1.getCart().getProductCount());
        } catch (QuantityExceededException e) {
            System.err.println("Error adding product to cart: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding to cart: " + e.getMessage());
        }

         System.err.println(" lets checkout \n\n");
        
         try 
        {
            checkout(customer1); 
        }
        catch (IllegalArgumentException e) 
        {
            System.err.println("Error during checkout: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            System.err.println("An unexpected error occurred during checkout: " + e.getMessage());
        }


      



    }

    public static void checkout(Customer customer) throws IllegalArgumentException {

    if (customer.getCart().getProducts().isEmpty()) {
       throw new IllegalArgumentException("Cart is empty. Please add products before checking out.");
    }
    System.out.println("** Shipment notice **");

    customer.getCart().getProducts().forEach((product, quantity) -> {
        System.out.println(quantity + "x " + product.name + "       " + product.weight * quantity + "kg ");
    });

    System.out.println();
    System.out.println("Total package weight: " + customer.getCart().getTotalWeight() + "kg\n\n");

    System.out.println("\n** Checkout receipt **");
    customer.getCart().getProducts().forEach((product, quantity) -> {
        System.out.println(quantity + "x " + product.name + "       " + product.price*quantity + "USD ");
    });

    System.out.println("--------------------------------------");
    System.out.println("Subtotal: " + customer.getCart().getTotalPrice() + " USD");
    System.out.println("Shipping: " + customer.getCart().getShiping() + " USD");
    System.out.println("Amount: " + (customer.getCart().getTotalPrice() + customer.getCart().getShiping()) + " USD\n\n");

    if (customer.getBalance() < (customer.getCart().getTotalPrice() + customer.getCart().getShiping())) {
        throw new IllegalArgumentException("Insufficient balance for checkout.");
    }
    else {
        customer.deductBalance(customer.getCart().getTotalPrice() + customer.getCart().getShiping());
        System.out.println("Checkout successful! Remaining balance: " + customer.getBalance() + " USD\n\n");
        
    }

    // shipping products
    ArrayList<Shippable> shippableProduct = new ArrayList<Shippable>();
    customer.getCart().getProducts().forEach((product, quantity) -> {
      if (product.shippable) {
            shippableProduct.add(product);
      } 
      
    });

    shippingService(shippableProduct);

}

public static void shippingService(ArrayList<Shippable> shippableProducts) {
    if (shippableProducts.isEmpty()) {
        System.out.println("No products to ship.");
        return;
    }
    System.out.println("Shipping the following products:");
    for (Shippable product : shippableProducts) {
        System.out.println(product.getName() + ": " + product.getWeight() + "kg");
    }
    System.out.println("All products shipped successfully!\n");
    
  
}

}
