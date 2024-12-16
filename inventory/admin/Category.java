package inventory.admin;

import inventory.common.InventoryItem;

public class Category extends InventoryItem {
    /* Constructor */
    public Category(int categoryId, String name) {
        super(categoryId, name);
    }

    /* Overriding the getDetails method from InventoryItem */
    @Override
    public String getDetails() {
        return "Category ID: " + getItemId() + ", Name: " + getName();
    }
}
