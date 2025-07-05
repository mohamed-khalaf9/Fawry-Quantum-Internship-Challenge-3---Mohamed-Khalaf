import java.time.LocalDate;

public class EcommerceSystem {
    public static  void main(String[] args) {
        runUseCases();

    }
    public static void runUseCases() {
        runFristUseCase();
    }
    public static void runFristUseCase() {
        System.out.println("\n--- Expired Product Use Case ---");
        try {
            Biscuits expiredBiscuits = new Biscuits("Old Biscuits", 5, 10.0, LocalDate.now().minusDays(1));
            Customer customer = new Customer(100.0, "Ahmed");
            Cart cart = new Cart();
            cart.add(expiredBiscuits, 1); // Should throw error
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
