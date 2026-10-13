package model;

public class Product {
    private final String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        if (id == null || id.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product ID and name are required.");
        }
        if (!Double.isFinite(price) || price < 0 || stock < 0) {
            throw new IllegalArgumentException("Invalid price or stock.");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid price.");
        }
        this.price = price;
    }

    public void reserveStock(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            throw new IllegalArgumentException("Invalid stock reservation.");
        }
        stock -= quantity;
    }

    public void releaseStock(int quantity) {
        if (quantity <= 0 || stock > Integer.MAX_VALUE - quantity) {
            throw new IllegalArgumentException("Invalid stock release.");
        }
        stock += quantity;
    }

    public int compareTo(Product other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name
                + "', price=" + price + ", stock=" + stock + "}";
    }
}
