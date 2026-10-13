
package service;

import exception.InsufficientStockException;
import exception.InvalidOrderException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import model.Customer;
import model.Inventory;
import model.Order;
import model.OrderItem;
import model.OrderStatus;
import model.Product;

public class OrderService {
    private final Inventory inventory;
    private final InventoryService inventoryService;
    private int nextOrderNumber = 1;

    public OrderService(Inventory inventory,
                        InventoryService inventoryService) {
        this.inventory = inventory;
        this.inventoryService = inventoryService;
    }

    public Order placeOrder(String customerId,
                            Map<String, Integer> requestedItems) {
        if (customerId == null || customerId.isBlank()
                || requestedItems == null || requestedItems.isEmpty()) {
            throw new InvalidOrderException(
                    "Customer and order items are required.");
        }

        Customer customer;
        try {
            customer = inventoryService.getCustomer(customerId);
        } catch (NoSuchElementException e) {
            throw new InvalidOrderException(e.getMessage());
        }

        // Validate every item before changing stock.
        Map<String, Integer> quantities = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : requestedItems.entrySet()) {
            String productId = entry.getKey();
            Integer quantity = entry.getValue();

            if (productId == null || productId.isBlank()
                    || quantity == null || quantity <= 0) {
                throw new InvalidOrderException(
                        "Product ID and positive quantity are required.");
            }

            try {
                inventoryService.getProduct(productId);
            } catch (NoSuchElementException e) {
                throw new InvalidOrderException(e.getMessage());
            }

            quantities.merge(productId, quantity, (a, b) -> {
                try {
                    return Math.addExact(a, b);
                } catch (ArithmeticException ex) {
                    throw new InvalidOrderException("Quantity is too large.");
                }
            });
        }

        // Check stock for all products before reserving any stock.
        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            Product product = inventoryService.getProduct(entry.getKey());

            if (product.getStock() < entry.getValue()) {
                throw new InsufficientStockException(
                        "Insufficient stock for " + product.getName()
                        + ". Available: " + product.getStock()
                        + ", requested: " + entry.getValue());
            }
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            Product product = inventoryService.getProduct(entry.getKey());

            orderItems.add(new OrderItem(
                    product.getId(),
                    product.getName(),
                    entry.getValue(),
                    product.getPrice()));
        }

        String orderId = "ORD-" + nextOrderNumber;
        Order order = new Order(orderId, customer.getId(), orderItems);

        // Reserve stock only after validation and order construction.
        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            inventoryService.reserveStock(entry.getKey(), entry.getValue());
        }

        inventory.getOrders().put(orderId, order);
        nextOrderNumber++;

        return order;
    }

    public Order getOrder(String orderId) {
        Order order = inventory.getOrders().get(orderId);

        if (order == null) {
            throw new NoSuchElementException("Order not found: " + orderId);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return List.copyOf(inventory.getOrders().values());
    }

    public void cancelOrder(String orderId) {
        Order order = getOrder(orderId);

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new InvalidOrderException(
                    "Order is already cancelled: " + orderId);
        }

        // Return all reserved stock.
        for (OrderItem item : order.getItems()) {
            inventoryService.releaseStock(
                    item.getProductId(), item.getQuantity());
        }

        order.cancel();
    }
}
