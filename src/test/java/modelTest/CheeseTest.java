package modelTest;

import model.Cheese;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CheeseTest {

    @Test
    void testValidCheeseCreation() {
        LocalDate expiry = LocalDate.of(2025, 12, 31);
        Cheese cheese = new Cheese("Gouda", 5, 30.0, 1.5, expiry);

        assertEquals("Gouda", cheese.getName());
        assertEquals(5, cheese.getQuantity());
        assertEquals(30.0, cheese.getPrice(), 0.001);
        assertEquals(1.5, cheese.getWeight(), 0.001);
        assertEquals(expiry, cheese.getExpiryDate());
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cheese("Cheddar", 3, 20.0, -2.0, LocalDate.now());
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testNullExpiryDateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cheese("Parmesan", 2, 25.0, 0.5, null);
        });
        assertEquals("Expiry date cannot be null", exception.getMessage());
    }

    @Test
    void testInheritedReduceQuantity() {
        Cheese cheese = new Cheese("Feta", 10, 15.0, 0.3, LocalDate.now().plusDays(30));
        cheese.reduceQuantity(3);
        assertEquals(7, cheese.getQuantity());
    }

    @Test
    void testReduceQuantityMoreThanAvailableThrowsException() {
        Cheese cheese = new Cheese("Brie", 5, 18.0, 0.25, LocalDate.now().plusDays(20));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cheese.reduceQuantity(6);
        });
        assertEquals("Not enough products", exception.getMessage());
    }
}

