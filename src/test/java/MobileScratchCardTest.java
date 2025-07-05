import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MobileScratchCardTest {

    @Test
    void testValidCreation() {
        MobileScratchCard card = new MobileScratchCard("Vodafone", 10, 50.0);
        assertEquals("Vodafone", card.getName());
        assertEquals(10, card.getQuantity());
        assertEquals(50.0, card.getPrice(), 0.001);
    }

    @Test
    void testEmptyNameThrowsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new MobileScratchCard("", 5, 10.0);});
        assertEquals("Product name cannot be empty", e.getMessage());
    }

    @Test
    void testNegativeQuantityThrowsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new MobileScratchCard("Etisalat", -1, 10.0);
        });
        assertEquals("qunatity must be a positive integer", e.getMessage());
    }

    @Test
    void testNegativePriceThrowsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new MobileScratchCard("Orange", 1, -10.0);
        });
        assertEquals("price must be a positive number", e.getMessage());
    }

    @Test
    void testReduceQuantitySuccessfully() {
        MobileScratchCard card = new MobileScratchCard("WE", 5, 20.0);
        card.reduceQuantity(2);
        assertEquals(3, card.getQuantity());
    }

    @Test
    void testReduceQuantityWithNegativeValueThrowsException() {
        MobileScratchCard card = new MobileScratchCard("WE", 5, 20.0);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            card.reduceQuantity(-1);
        });
        assertEquals("Quantity cannot be negative", e.getMessage());
    }

    @Test
    void testReduceQuantityMoreThanAvailableThrowsException() {
        MobileScratchCard card = new MobileScratchCard("WE", 5, 20.0);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            card.reduceQuantity(6);});
        assertEquals("Not enough products", e.getMessage());
    }
}

