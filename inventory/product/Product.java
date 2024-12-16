package inventory.product;

import inventory.common.InventoryItem;

public class Product extends InventoryItem {
    private String category;
    private String productionDate;
    private String expirationDate;
    private int quantity;
    private double price;

    /* Constructor */
    public Product(int productId, String name, String category, String productionDate, String expirationDate, int quantity, double price) {
        super(productId, name);
        this.category = category;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.price = price;
    }

    /* Getters and Setters */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /* Overriding the getDetails method from InventoryItem */
    @Override
    public String getDetails() {
        return "Product ID: " + getItemId() + ", Name: " + getName() + ", Category: " + category +
               ", Production Date: " + productionDate + ", Expiration Date: " + expirationDate +
               ", Quantity: " + quantity + ", Price: " + price;
    }
}
