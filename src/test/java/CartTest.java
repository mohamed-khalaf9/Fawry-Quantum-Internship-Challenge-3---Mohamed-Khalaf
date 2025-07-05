import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class CartTest {

    @Test
    void testAddValidNonExpirableProduct() {
        Cart cart = new Cart();
        Product tv = new TV("LG", 10, 3000.0, 8.0);

        cart.add(tv, 2);
        List<CartItem> items = cart.getItems();

        assertEquals(1, items.size());
        assertEquals(tv, items.get(0).getProduct());
        assertEquals(2, items.get(0).getQuantity());
    }

    @Test
    void testAddValidExpirableProduct() {
        Cart cart = new Cart();
        Product cheese = new Cheese("Cheddar", 5, 40.0, 0.5, LocalDate.now().plusDays(7));

        cart.add(cheese, 1);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void testAddWithNullProductThrowsException() {
        Cart cart = new Cart();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            cart.add(null, 1);
        });
        assertEquals("Product cannot be null", ex.getMessage());
    }

    @Test
    void testAddWithNonPositiveQuantityThrowsException() {
        Cart cart = new Cart();
        Product tv = new TV("Samsung", 10, 5000.0, 12.0);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            cart.add(tv, 0);
        });
        assertEquals("Quantity must be greater than 0", ex.getMessage());
    }

    @Test
    void testAddExpiredProductThrowsException() {
        Cart cart = new Cart();
        Product expiredCheese = new Cheese("Old Brie", 3, 25.0, 0.3, LocalDate.now().minusDays(1));

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            cart.add(expiredCheese, 1);
        });
        assertEquals("The Product is expired", ex.getMessage());
    }

    @Test
    void testAddMoreThanAvailableStockThrowsException() {
        Cart cart = new Cart();
        Product tv = new TV("Sony", 2, 4500.0, 10.0);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            cart.add(tv, 3);
        });
        assertEquals("The stock not enough", ex.getMessage());
    }

    @Test
    void testMultipleAdditions() {
        Cart cart = new Cart();
        Product tv = new TV("Toshiba", 5, 2000.0, 9.0);
        Product cheese = new Cheese("Feta", 3, 18.0, 0.2, LocalDate.now().plusDays(3));

        cart.add(tv, 2);
        cart.add(cheese, 1);

        assertEquals(2, cart.getItems().size());
    }
}

