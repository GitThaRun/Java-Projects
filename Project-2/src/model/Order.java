package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderItem> items;
    private final double totalAmount;
    private OrderStatus status;

    public Order(String id, String customerId, List<OrderItem> items) {
        if (id == null || id.isBlank()
                || customerId == null || customerId.isBlank()
                || items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Invalid order details.");
        }

        this.id = id;
        this.customerId = customerId;
        this.items = Collections.unmodifiableList(
                new ArrayList<>(items));

        double total = 0;
        for (OrderItem item : this.items) {
            total += item.getSubtotal();
        }

        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException("Order total is too large.");
        }

        this.totalAmount = total;
        this.status = OrderStatus.PLACED;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }

    public void cancel() {
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled.");
        }
        status = OrderStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', customerId='" + customerId
                + "', totalAmount=" + totalAmount
                + ", status=" + status + ", items=" + items + "}";
    }
}
