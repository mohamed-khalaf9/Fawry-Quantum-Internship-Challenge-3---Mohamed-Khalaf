import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BiscuitsTest {

    @Test
    void testValidBiscuitsCreation() {
        LocalDate expiry = LocalDate.of(2025, 12, 31);
        Biscuits biscuit = new Biscuits("Oreo", 10, 15.0, expiry);

        assertEquals("Oreo", biscuit.getName());
        assertEquals(10, biscuit.getQuantity());
        assertEquals(15.0, biscuit.getPrice(), 0.001);
        assertEquals(expiry, biscuit.getExpiryDate());
    }

    @Test
    void testNullExpiryDateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Biscuits("Bourbon", 5, 10.0, null);
        });
        assertEquals("Expiry date cannot be null", exception.getMessage());
    }

    @Test
    void testReduceQuantitySuccessfully() {
        Biscuits biscuit = new Biscuits("Digestive", 6, 12.0, LocalDate.now().plusDays(60));
        biscuit.reduceQuantity(2);
        assertEquals(4, biscuit.getQuantity());
    }

    @Test
    void testReduceQuantityMoreThanAvailableThrowsException() {
        Biscuits biscuit = new Biscuits("Marie", 3, 8.0, LocalDate.now().plusDays(30));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            biscuit.reduceQuantity(4);
        });
        assertEquals("Not enough products", exception.getMessage());
    }

    @Test
    void testReduceNegativeQuantityThrowsException() {
        Biscuits biscuit = new Biscuits("Tiger", 4, 9.0, LocalDate.now().plusDays(20));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            biscuit.reduceQuantity(-1);
        });
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }
}
