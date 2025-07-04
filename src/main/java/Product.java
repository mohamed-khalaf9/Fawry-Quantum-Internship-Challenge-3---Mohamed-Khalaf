public abstract class Product {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int qunatity, double price) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");
        if(qunatity < 0)
            throw new IllegalArgumentException("qunatity must be a positive integer");
        if(price < 0.0)
            throw new IllegalArgumentException("price must be a positive number");
        this.name = name;
        this.quantity = qunatity;
        this.price = price;
    }





}
