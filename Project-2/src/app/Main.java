
package app;

import exception.InsufficientStockException;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Customer;
import model.Inventory;
import model.Order;
import model.Product;
import service.InventoryService;
import service.OrderService;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InventoryService inventoryService =
                new InventoryService(inventory);
        OrderService orderService =
                new OrderService(inventory, inventoryService);

        inventoryService.addProduct(
                new Product("P101", "Keyboard", 1200.00, 10));
        inventoryService.addProduct(
                new Product("P102", "Mouse", 500.00, 20));
        inventoryService.addProduct(
                new Product("P103", "Monitor", 8500.00, 5));

        inventoryService.addCustomer(
                new Customer("C101", "Tharun", "tharun@example.com"));

        System.out.println("=== All Products ===");
        inventoryService.getAllProducts().forEach(System.out::println);

        System.out.println("\n=== Products Sorted by Price ===");
        inventoryService.sortByPriceAscending()
                .forEach(System.out::println);

        System.out.println("\n=== Search: Mouse ===");
        inventoryService.searchByName("Mouse")
                .forEach(System.out::println);

        
        Map<String, Integer> requestedItems = new LinkedHashMap<>();
        requestedItems.put("P101", 2);
        requestedItems.put("P102", 1);

        Order order = orderService.placeOrder("C101", requestedItems);

        System.out.println("\n=== Order Placed ===");
        System.out.println(order);

        System.out.println("\n=== Stock After Order ===");
        inventoryService.getAllProducts().forEach(System.out::println);

        
        try {
            Map<String, Integer> invalidItems = new LinkedHashMap<>();
            invalidItems.put("P103", 100);

            orderService.placeOrder("C101", invalidItems);
        } catch (InsufficientStockException e) {
            System.out.println("\nOrder rejected: " + e.getMessage());
        }

       
        orderService.cancelOrder(order.getId());

        System.out.println("\n=== Order After Cancellation ===");
        System.out.println(orderService.getOrder(order.getId()));

        System.out.println("\n=== Stock After Cancellation ===");
        inventoryService.getAllProducts().forEach(System.out::println);
    }
}
