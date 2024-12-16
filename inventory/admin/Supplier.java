package inventory.admin;

import inventory.common.InventoryItem;

public class Supplier extends InventoryItem {
    private String contactInfo;

    /* Constructor */
    public Supplier(int supplierId, String name, String contactInfo) {
        super(supplierId, name);
        this.contactInfo = contactInfo;
    }

    /* Getters and Setters */
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /* Overriding the getDetails method from InventoryItem */
    @Override
    public String getDetails() {
        return "Supplier ID: " + getItemId() + ", Name: " + getName() + ", Contact Info: " + contactInfo;
    }
}
