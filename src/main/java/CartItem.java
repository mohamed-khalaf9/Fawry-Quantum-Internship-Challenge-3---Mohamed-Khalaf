public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if(product == null)
            throw new NullPointerException("product is null");
        if(quantity < 0)
            throw new IllegalArgumentException("quantity is negative");
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return this.product;
    }
    public int getQuantity() {
        return this.quantity;
    }


}
