package com.fawry;

import java.time.LocalDate;
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
        try {
            // Adding a product with valid parameters
            product1 = new Product("Chocolate", 2.50, 100, false, 0.2);
            product2 = new Product("Milk", 1.50, 200, true, 1.0, LocalDate.of(2026, 12, 31));
            inventory.addProduct(product1, 50);
            inventory.addProduct(product2, 30);
            System.out.println("Product added: " + product1.name);
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

        Cart cart = new Cart();
        try {
            // Adding products to the cart
            cart.addProduct(product1, 2);
            cart.addProduct(product2, 3);
            System.out.println("Total price in cart: " + cart.getTotalPrice() + " USD");
            System.out.println("Total product count in cart: " + cart.getProductCount());
        } catch (QuantityExceededException e) {
            System.err.println("Error adding product to cart: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding to cart: " + e.getMessage());
        }

         System.err.println(" lets checkout \n\n");
        
         try 
        {
            checkout(cart); 
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

    public static void checkout(Cart cart) throws IllegalArgumentException {

    if (cart.getProducts().isEmpty()) {
       throw new IllegalArgumentException("Cart is empty. Please add products before checking out.");
    }
    System.out.println("** Shipment notice **");

    cart.getProducts().forEach((product, quantity) -> {
        System.out.println(quantity + "x " + product.name + "       " + product.weight * quantity + "kg ");
    });

    System.out.println();
    System.out.println("Total package weight: " + cart.getTotalWeight() + "kg");

    System.out.println("\n** Checkout receipt **");
    cart.getProducts().forEach((product, quantity) -> {
        System.out.println(quantity + "x " + product.name + "       " + product.price*quantity + "USD ");
    });

    System.out.println("--------------------------------------");
    System.out.println("Subtotal: " + cart.getTotalPrice() + " USD");
    System.out.println("Shipping: " + cart.getShiping() + " USD");
    System.out.println("Amount: " + (cart.getTotalPrice() + cart.getShiping()) + " USD");


}



    
  
}
