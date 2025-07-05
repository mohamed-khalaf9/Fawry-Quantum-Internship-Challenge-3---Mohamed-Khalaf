package serviceTest;

import interfaces.ShippableItem;
import model.Cheese;
import model.TV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ShippingService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShippingServiceTest {

    private ShippingService shippingService;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        shippingService = new ShippingService();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output)); // Redirect System.out
    }

    @Test
    void testShipWithEmptyList() {
        shippingService.ship(List.of());

        String result = output.toString().trim();
        assertTrue(result.contains("No shippable items found"));
    }

    @Test
    void testShipWithNullList() {
        shippingService.ship(null);

        String result = output.toString().trim();
        assertTrue(result.contains("No shippable items found"));
    }

    @Test
    void testShipWithSingleShippableItem() {
        ShippableItem tv = new TV("Samsung", 5, 3000.0, 12.5);
        shippingService.ship(List.of(tv, tv, tv)); // 3 TVs

        String result = output.toString().trim();

        assertTrue(result.contains("** Shipment notice **"));
        assertTrue(result.contains("3x Samsung 37500g")); // 3 * 12.5kg * 1000g
        assertTrue(result.contains("Total package weight 37.5kg"));
    }

    @Test
    void testShipWithMultipleDifferentItems() {
        ShippableItem tv = new TV("LG model.TV", 3, 2500.0, 10.0);
        ShippableItem cheese = new Cheese("Gouda", 5, 80.0, 0.5, LocalDate.now().plusDays(3));

        List<ShippableItem> items = List.of(tv, tv, cheese, cheese, cheese);

        shippingService.ship(items);

        String result = output.toString().trim();

        assertTrue(result.contains("2x LG model.TV 20000g"));     // 2 TVs * 10.0kg
        assertTrue(result.contains("3x Gouda 1500g"));       // 3 model.Cheese * 0.5kg
        assertTrue(result.contains("Total package weight 21.5kg"));
    }
}
