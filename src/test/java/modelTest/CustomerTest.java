package modelTest;

import model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void testValidCustomerCreation() {
        Customer customer = new Customer(1000.0, "Mohamed");
        assertEquals("Mohamed", customer.getName());
        assertEquals(1000.0, customer.getCurrentBalance(), 0.001);
    }

    @Test
    void testNegativeBalanceThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(-100.0, "Ali");
        });
        assertEquals("Invalid balance", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(500.0, null);
        });
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void testEmptyNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer(500.0, "");
        });
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void testValidWithdraw() {
        Customer customer = new Customer(1000.0, "Salma");
        customer.withdraw(300.0);
        assertEquals(700.0, customer.getCurrentBalance(), 0.001);
    }

    @Test
    void testWithdrawNegativeAmountThrowsException() {
        Customer customer = new Customer(1000.0, "Salma");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.withdraw(-50.0);
        });
        assertEquals("Invalid amount", exception.getMessage());
    }

    @Test
    void testWithdrawMoreThanBalanceThrowsException() {
        Customer customer = new Customer(100.0, "Khalid");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.withdraw(200.0);
        });
        assertEquals("Invalid amount", exception.getMessage());
    }
}
