package inventory.client;

import inventory.common.InventoryItem;

public class Order extends InventoryItem {
    private int quantity;
    private String orderDate;
    private String status; // e.g., "Pending", "Completed", etc.

    /* Constructor */
    public Order(int orderId, String name, int quantity, String orderDate, String status) {
        super(orderId, name);
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    /* Getters and Setters */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* Overriding the getDetails method from InventoryItem */
    @Override
    public String getDetails() {
        return "Order ID: " + getItemId() + ", Product Name: " + getName() + ", Quantity: " + quantity +
               ", Order Date: " + orderDate + ", Status: " + status;
    }
}
