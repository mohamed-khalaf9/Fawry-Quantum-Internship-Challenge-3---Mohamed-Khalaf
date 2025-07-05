
 # Fawry Rise Journey - Full Stack Development Internship Challenge --- mohamed.khalaf8340@gmail.com

#  Fawry Rise Journey - E-Commerce System
A console-based e-commerce system implemented in **Java** for the Fawry Full Stack Development Internship Challenge. The application models expiry, shippability, cart logic, and checkout processes, including real-world validation and behaviors.

## Table of Contents

- [Features](#features)
- [Class Diagram](#class-diagram)
- [Project Structure](#project-structure)
- [Test Coverage](#test-coverage)
- [Design Decisions](#design-decisions)
- [Demo Video (with voice)](#demo-video-with-voice)
- [How to Run](#how-to-run)
- [Developed By](#developed-by)

---

##  Features

- **Product Management**
  - Each product has: `name`, `price`, and `quantity`.
  - Expirable items: Cheese, Biscuits.
  - Shippable items (with weight): Cheese, TV, Mobile.

- **Cart Functionality**
    - Add products with specific quantity.
    - Quantity validation based on available stock.
    - Handles edge cases like expired products.

-  **Checkout Process**
  - Validates:
    - Cart is not empty
    - Products are in stock
    - Products are not expired
    - Customer has enough balance
  - Calculates:
    - Subtotal
    - Shipping fees (based on weight)
    - Total amount
    - Customer balance after payment

-  **Shipping**
  - Collects shippable items using interface with:
    - `String getName()`
    - `double getWeight()`
  - Outputs detailed shipment notice.

---

##  Class Diagram
> [!TIP]
> High Quality diagram with all details
> [EcommerceFawry.drawio (1).pdf](https://github.com/user-attachments/files/21076004/EcommerceFawry.drawio.1.pdf)


![EcommerceFawry drawio](https://github.com/user-attachments/assets/577b4a2e-a216-4e56-b7ee-ceb743972ca9)


---

### Project Structure

```
ecommerce-system/
├── Product.java              # Abstract base class for all products
├── Cheese.java               # Expirable and shippable product
├── Biscuits.java             # Expirable product
├── TV.java                   # Shippable product
├── Mobile.java               # Shippable product
├── MobileScratchCard.java    # Non-expirable, non-shippable product
├── Cart.java                 # Handles adding and storing cart items
├── CartItem.java             # Represents a product and its quantity in the cart
├── Customer.java             # Represents a customer with a balance
├── CheckoutService.java      # Handles the full checkout logic
├── ShippingService.java      # Handles and prints shipping information
├── EcommerceSystem.java      # Main class to run different use case scenarios
├── Expirable.java            # Interface for products with expiry date
├── Shippable.java            # Interface for products with weight
├── ShippableItem.java        # Interface used by the shipping service
├── test/                     # Contains unit test classes
│   ├── ...                   # Tests for Cart, CheckoutService, Product, etc.
```

The project follows a flat structure for simplicity, keeping all core classes in a single package and tests in a separate `test/` directory.


##  Test Coverage

The project includes a dedicated test/ folder containing comprehensive unit tests that validate both the normal and exceptional behaviors of the system. Each method is tested for correct functionality under valid conditions, as well as for proper handling of edge cases and invalid input (e.g., expired products, out-of-stock items, insufficient balance, and empty cart scenarios). The tests also cover shipping fee calculation and full checkout flows involving multiple products. Running the tests is highly encouraged to explore the system's reliability, catch potential issues early, and confirm that all key use cases behave as expected.

---

###  Design Decisions

To ensure a clean, maintainable, and scalable design, I followed key object-oriented and SOLID design principles while building the system. Below is a breakdown of the major design choices:

- **Abstract `Product` Class**  
  I chose to implement `Product` as an abstract class to act as the parent for all specific product types (e.g., `Cheese`, `Mobile`, `TV`, `Biscuits`). This allows sharing of common fields like `name`, `price`, and `quantity`, while letting subclasses define unique behaviors.

- **Behavioral Interfaces – `Expirable`, `Shippable`, `ShippableItem`**  
  Instead of placing `expiryDate` and `weight` in the `Product` class, I used interfaces to model these optional traits. This prevents adding unnecessary attributes to products that don’t require them, keeping the class design clean. This decision aligns with the **Interface Segregation Principle**, avoiding bloated classes and improving flexibility.

- **`CartItem` Abstraction**  
  A `CartItem` class was introduced to represent a pair of a product and its quantity. While initially simple, this abstraction allows future extensibility (e.g., adding discount information or tracking purchase timestamps) and keeps the cart logic modular.

- **`Cart` Class**  
  The `Cart` holds a list of `CartItem` objects and provides an `add` method to insert products with their respective quantities. Validation is performed to ensure quantities are available and products are not expired.

- **`CheckoutService` Separation**  
  To maintain separation of concerns, all checkout-related logic is handled inside a dedicated `CheckoutService` class. It contains the `checkout()` method along with utility methods for validation, stock updates, shipping calculations, and receipt generation. This improves modularity and testability.

- **Shipping Logic Workaround**  
  As per the requirement, `ShippingService` accepts only a list of `ShippableItem` objects that expose `getName()` and `getWeight()`. Since quantity is not part of this contract, I implemented a workaround: in `CheckoutService`, I generated a list that duplicates each shippable item based on its quantity. This list is passed to `ShippingService`, which then groups identical items to reconstruct the quantities and print them correctly. This design respects the interface contract while still fulfilling the functional requirement.

- **Console Menu in `EcommerceSystem`**  
  The `EcommerceSystem` class features a console-based menu to allow users to trigger different use cases, including both valid and exceptional flows. This approach provides a user-friendly way to explore the system and keeps the application running even when exceptions occur.


---

##  Demo Video (with voice)
https://drive.google.com/file/d/1SMkMLOLJLfje5-LZuuTCz0JYpp-lbGc4/view?usp=sharing

---

---

### How to Run

1. **Clone the repository**:
   ```bash
   https://github.com/mohamed-khalaf9/Fawry-Quantum-Internship-Challenge-3---Mohamed-Khalaf.git
   ```

2. **Compile the code**:
   ```bash
   javac *.java
   ```

3. **Run the application**:
   ```bash
   java EcommerceSystem
   ```

> The application will launch a menu that allows you to run different use cases including valid and exception scenarios.

---

### Developed By

**Mohamed Khalaf**  
Third-Year CS Student – Backend Developer (Java Stack)  
 Egypt  
📧 [mohamed.khalaf8340@gmail.com] | 🔗 [https://www.linkedin.com/in/mohamed-khalafcs111/] 


