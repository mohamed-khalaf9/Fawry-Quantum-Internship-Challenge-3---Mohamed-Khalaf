import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<CartItem>();
    }

    public void add(Product product, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(product instanceof Expirable) {
            if(((Expirable) product).getExpiryDate().isBefore(LocalDate.now()))
                throw new IllegalArgumentException("The Product is expired");
        }
        if(quantity > product.getQuantity()) {
            throw new IllegalArgumentException("The stock not enough");
        }
        CartItem item = new CartItem(product, quantity);
        items.add(item);

    }
    public List<CartItem> getItems() {
        return items;
    }
}
