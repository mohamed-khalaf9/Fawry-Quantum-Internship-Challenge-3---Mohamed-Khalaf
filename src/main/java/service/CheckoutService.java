package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;
import interfaces.*;

public class CheckoutService {
    private static final double SHIPPING_FEES_PER_KG = 10.0;

    public void checkout(Customer customer, Cart cart) {
        validateInputs(customer, cart);

        List<CartItem> cartItems = cart.getItems();
        if(cartItems.isEmpty())
            throw new IllegalStateException("service.Cart is empty.");

        validateNoExpiredProductsExist(cartItems);

        List<ShippableItem> shippableItems = getShippableItems(cartItems);
        double subtotal = calculateSubtotal(cartItems);
        double shippingFees = calculateShippingFees(cartItems);
        double totalAmount = subtotal + shippingFees;

        validateCustomerBalance(customer, totalAmount);
        customer.withdraw(totalAmount);

        updateStock(cartItems);

        ShippingService shippingService = new ShippingService();
        shippingService.ship(shippableItems);


        printCheckoutReceipt(cartItems, subtotal, shippingFees, totalAmount);
    }

    //::::::::::::::::::::::::::: Utilities :::::::::::::::::::::::::::::::::::::::::::::::::::::

    public void updateStock(List<CartItem> cartItems) {
        for(CartItem cartItem : cartItems) {
            int requestedQuantity = cartItem.getQuantity();
            Product product = cartItem.getProduct();
            product.reduceQuantity(requestedQuantity);
        }
    }

    public List<ShippableItem> getShippableItems(List<CartItem> cartItems) {
        List<ShippableItem> shippableItems = new ArrayList<>();
        for(CartItem cartItem : cartItems) {
            if(cartItem.getProduct() instanceof ShippableItem shippableItem) {
                for(int quantity = 0; quantity < cartItem.getQuantity(); quantity++) {
                    shippableItems.add(shippableItem);
                }
            }
        }
        return shippableItems;
    }

    private void validateInputs(Customer customer, Cart cart) {
        if (customer == null || cart == null) {
            throw new IllegalArgumentException("model.Customer or cart cannot be null.");
        }
    }



    private void validateNoExpiredProductsExist(List<CartItem> items) {
        for (CartItem item : items) {
            Product product = item.getProduct();
            LocalDate currentDate = LocalDate.now();
            if (product instanceof Expirable expirable && expirable.getExpiryDate().isBefore(currentDate)) {
                throw new IllegalStateException(product.getName() + " is expired.");
            }
        }
    }

    private void validateCustomerBalance(Customer customer, double amount) {
        if (customer.getCurrentBalance() < amount) {
            throw new IllegalStateException("Insufficient balance to complete checkout.");
        }
    }



    private double calculateSubtotal(List<CartItem> items) {
        double subtotal = 0.0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    private double calculateShippingFees(List<CartItem> items) {
        double shippingFees = 0.0;
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof ShippableItem shippable) {
                shippingFees += shippable.getWeight() * item.getQuantity() * SHIPPING_FEES_PER_KG;
            }
        }
        return shippingFees;
    }



    private void printCheckoutReceipt(List<CartItem> items, double subtotal, double shippingFees, double total) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            System.out.printf("%dx %s %.0f%n", item.getQuantity(), item.getProduct().getName(),
                    itemTotal);}

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFees);
        System.out.printf("Amount %.0f%n", total);
    }
}
