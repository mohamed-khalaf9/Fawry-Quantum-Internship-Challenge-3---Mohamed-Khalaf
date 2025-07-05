import java.util.*;

public class ShippingService {

    public void ship(List<ShippableItem> items) {
        System.out.println("** Shipment notice **");

        if (items == null || items.isEmpty()) {
            System.out.println("No shippable items found");
            return;
        }

        Map<ShippableItem, Integer> itemCounts = countShippableItems(items);
        double totalWeight = 0.0;



        for (Map.Entry<ShippableItem, Integer> entry : itemCounts.entrySet()) {
            ShippableItem item = entry.getKey();
            int quantity = entry.getValue();
            double weight = item.getWeight();
            totalWeight += weight * quantity;

            System.out.printf("%dx %s %.0fg%n", quantity, item.getName(), weight * quantity * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    private  Map<ShippableItem, Integer> countShippableItems(List<ShippableItem> items) {
        Map<ShippableItem, Integer> countMap = new LinkedHashMap<>();

        for (ShippableItem item : items) {
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }

        return countMap;
    }

}
