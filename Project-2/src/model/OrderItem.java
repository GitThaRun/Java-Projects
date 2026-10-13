package model;
public class OrderItem {
    private final String productId;
    private final String productName;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(String productId, String productName,
                     int quantity, double unitPrice) {
        if (productId == null || productId.isBlank()
                || productName == null || productName.isBlank()
                || quantity <= 0
                || !Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Invalid order item.");
        }

        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return productName + " x " + quantity + " = " + getSubtotal();
    }
}
