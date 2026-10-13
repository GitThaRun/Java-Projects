
package service;

import exception.InsufficientStockException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import model.Customer;
import model.Inventory;
import model.Product;

public class InventoryService {
    private final Inventory inventory;

    public InventoryService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addProduct(Product product) {
        if (inventory.getProducts().containsKey(product.getId())) {
            throw new IllegalArgumentException("Product ID already exists.");
        }
        inventory.getProducts().put(product.getId(), product);
    }

    public void addCustomer(Customer customer) {
        if (inventory.getCustomers().containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer ID already exists.");
        }
        inventory.getCustomers().put(customer.getId(), customer);
    }

    public Product getProduct(String productId) {
        Product product = inventory.getProducts().get(productId);

        if (product == null) {
            throw new NoSuchElementException("Product not found: " + productId);
        }
        return product;
    }

    public Customer getCustomer(String customerId) {
        Customer customer = inventory.getCustomers().get(customerId);

        if (customer == null) {
            throw new NoSuchElementException(
                    "Customer not found: " + customerId);
        }
        return customer;
    }

    public List<Product> getAllProducts() {
        return inventory.getProducts().values().stream().toList();
    }

    public List<Product> sortByName() {
        return getAllProducts().stream()
                .sorted()
                .toList();
    }

    public List<Product> sortByPriceAscending() {
        return getAllProducts().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toList();
    }

    public List<Product> sortByPriceDescending() {
        return getAllProducts().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice)
                        .reversed())
                .toList();
    }

    public List<Product> filterByMinimumStock(int minimumStock) {
        if (minimumStock < 0) {
            throw new IllegalArgumentException("Minimum stock cannot be negative.");
        }

        return getAllProducts().stream()
                .filter(product -> product.getStock() >= minimumStock)
                .toList();
    }

    public List<Product> searchByName(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Search keyword is required.");
        }

        return getAllProducts().stream()
                .filter(product -> product.getName()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .toList();
    }

    public void reserveStock(String productId, int quantity) {
        Product product = getProduct(productId);

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        if (product.getStock() < quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock for " + product.getName()
                    + ". Available: " + product.getStock());
        }

        product.reserveStock(quantity);
    }

    public void releaseStock(String productId, int quantity) {
        getProduct(productId).releaseStock(quantity);
    }
}
