import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private Customer customer;
    private Cart cart;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        customer = new Customer(5000.0, "Mohamed");
        cart = new Cart();
    }

    @Test
    void testCheckoutWithValidCart() {
        Product tv = new TV("LG", 2, 1000.0, 10.0);
        cart.add(tv, 1);

        checkoutService.checkout(customer, cart);

        assertEquals(1, tv.getQuantity()); // Stock reduced
        double expectedBalance = 5000.0 - 1000.0 - (10 * 10); // product + shipping
        assertEquals(expectedBalance, customer.getCurrentBalance(), 0.001);
    }

    @Test
    void testAddExpiredProductThrowsException() {
        Product cheese = new Cheese("Old Cheese", 1, 100.0, 0.3, LocalDate.now().minusDays(1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.add(cheese, 1);
        });

        assertEquals("The Product is expired", exception.getMessage());
    }


    @Test
    void testCheckoutWithInsufficientBalanceThrowsException() {
        Customer poorCustomer = new Customer(50.0, "Ali");
        Product tv = new TV("Sony", 1, 100.0, 2.0);
        cart.add(tv, 1); // Total will be 100 + 20 = 120

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            checkoutService.checkout(poorCustomer, cart);
        });

        assertEquals("Insufficient balance to complete checkout.", exception.getMessage());
    }

    @Test
    void testGetShippableItemsReturnsCorrectCount() {
        Product tv = new TV("Samsung", 5, 400.0, 8.0);
        Product cheese = new Cheese("Gouda", 3, 80.0, 0.3, LocalDate.now().plusDays(10));
        cart.add(tv, 2);
        cart.add(cheese, 1);

        List<ShippableItem> shippables = checkoutService.getShippableItems(cart.getItems());

        assertEquals(3, shippables.size()); // 2 TVs + 1 Cheese
    }

    @Test
    void testCartIsEmptyThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            checkoutService.checkout(customer, cart);
        });

        assertEquals("Cart is empty.", exception.getMessage());
    }

    @Test
    void testNullCustomerOrCartThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            checkoutService.checkout(null, cart);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            checkoutService.checkout(customer, null);
        });
    }

    @Test
    void testUpdateStockReducesQuantitiesCorrectly() {
        Product tv = new TV("Toshiba", 3, 700.0, 12.0);
        cart.add(tv, 2);
        checkoutService.updateStock(cart.getItems());

        assertEquals(1, tv.getQuantity());
    }
}
