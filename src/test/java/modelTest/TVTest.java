package modelTest;

import model.TV;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TVTest {

    @Test
    void testValidTVCreation() {
        TV tv = new TV("Samsung", 3, 5000.0, 12.5);

        assertEquals("Samsung", tv.getName());
        assertEquals(3, tv.getQuantity());
        assertEquals(5000.0, tv.getPrice(), 0.001);
        assertEquals(12.5, tv.getWeight(), 0.001);
    }

    @Test
    void testNegativeWeightThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TV("LG", 2, 4500.0, -5.0);
        });
        assertEquals("Weight cannot be negative", exception.getMessage());
    }

    @Test
    void testReduceQuantitySuccessfully() {
        TV tv = new TV("Sony", 4, 6000.0, 10.0);
        tv.reduceQuantity(2);
        assertEquals(2, tv.getQuantity());
    }

    @Test
    void testReduceQuantityMoreThanAvailableThrowsException() {
        TV tv = new TV("Toshiba", 2, 3000.0, 9.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tv.reduceQuantity(3);
        });
        assertEquals("Not enough products", exception.getMessage());
    }

    @Test
    void testReduceQuantityWithNegativeThrowsException() {
        TV tv = new TV("Sharp", 5, 3500.0, 8.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tv.reduceQuantity(-1);
        });
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }
}

