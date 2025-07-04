public class Cheese extends  Product implements Shippable, ShippableItem,Expirable {
    private double weight;

    public Cheese(String name, int qunatity, double price, double weight) {
        super(name, qunatity, price);
        if(weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
        this.weight = weight;
    }


}
