import java.time.LocalDate;
import java.util.Scanner;

public class EcommerceSystem {
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======= E-Commerce System Use Cases =======");
            System.out.println("1. Run Valid Use Case");
            System.out.println("2. Run Empty Cart Case");
            System.out.println("3. Run Insufficient Balance Case");
            System.out.println("4. Run Out of Stock Case");
            System.out.println("5. Run Expired Product Case");
            System.out.println("0. Exit");
            System.out.print("Choose use case: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> runValidUseCase();
                case 2 -> runEmptyCartCase();
                case 3 -> runInsufficientBalanceCase();
                case 4 -> runOutOfStockCase();
                case 5 -> runExpiredProductCase();
                case 0 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static void runValidUseCase() {
        System.out.println("\n--- Running Valid Use Case ---");
        try {
            Cheese cheese = new Cheese("Cheddar Cheese", 10, 50.0, 0.5, LocalDate.now().plusDays(10));
            TV tv = new TV("Samsung 42-inch", 5, 3000.0, 8.0);
            MobileScratchCard scratchCard = new MobileScratchCard("Orange Scratch Card", 20, 20.0);
            Customer customer = new Customer(10000.0, "Mohamed");
            Cart cart = new Cart();

            cart.add(cheese, 2);
            cart.add(tv, 3);
            cart.add(scratchCard, 1);

            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void runEmptyCartCase() {
        System.out.println("\n--- Running Empty Cart Case ---");
        try {
            Customer customer = new Customer(1000.0, "Hassan");
            Cart cart = new Cart(); // empty cart

            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void runInsufficientBalanceCase() {
        System.out.println("\n--- Running Insufficient Balance Case ---");
        try {
            Mobile mobile = new Mobile("iPhone", 5, 7000.0, 0.3);
            Customer customer = new Customer(2000.0, "Alaa");
            Cart cart = new Cart();

            cart.add(mobile, 1);

            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void runOutOfStockCase() {
        System.out.println("\n--- Running Out of Stock Case ---");
        try {
            TV tv = new TV("LG OLED", 1, 5000.0, 10.0); // only 1 in stock
            Customer customer = new Customer(20000.0, "Sarah");
            Cart cart = new Cart();

            cart.add(tv, 2);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void runExpiredProductCase() {
        System.out.println("\n--- Running Expired Product Case ---");
        try {
            Biscuits biscuits = new Biscuits("Expired Biscuits", 5, 10.0, LocalDate.now().minusDays(3));
            Customer customer = new Customer(1000.0, "Nour");
            Cart cart = new Cart();

            cart.add(biscuits, 1);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
