package model;
import java.util.LinkedHashMap;
import java.util.Map;
public class Inventory {
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final Map<String, Order> orders = new LinkedHashMap<>();

    public Map<String, Product> getProducts() {
        return products;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }
}
