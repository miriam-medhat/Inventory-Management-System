package inventory.client;

import inventory.common.InventoryItem;

public class Client extends InventoryItem {
    private String contactInfo;
    private String address;

    /* Constructor */
    public Client(int clientId, String name, String contactInfo, String address) {
        super(clientId, name);
        this.contactInfo = contactInfo;
        this.address = address;
    }

    /* Getters and Setters */
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /* Overriding the getDetails method from InventoryItem */
    @Override
    public String getDetails() {
        return "Client ID: " + getItemId() + ", Name: " + getName() + ", Contact Info: " + contactInfo + ", Address: " + address;
    }
}
