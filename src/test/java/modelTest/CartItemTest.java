package modelTest;

import model.CartItem;
import model.Product;
import model.TV;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    void testValidCartItemCreation() {
        Product product = new TV("Samsung", 3, 5000.0, 12.5);
        CartItem item = new CartItem(product, 2);

        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void testNullProductThrowsException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new CartItem(null, 1);
        });
        assertEquals("product is null", exception.getMessage());
    }

    @Test
    void testNegativeQuantityThrowsException() {
        Product product = new TV("LG", 2, 4500.0, 10.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, -1);
        });
        assertEquals("quantity is negative", exception.getMessage());
    }

    @Test
    void testGettersReturnCorrectValues() {
        Product product = new TV("Sony", 5, 6000.0, 11.0);
        CartItem item = new CartItem(product, 3);

        assertSame(product, item.getProduct());
        assertEquals(3, item.getQuantity());
    }
}

