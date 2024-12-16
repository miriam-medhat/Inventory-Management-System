package inventory.common;

public abstract class InventoryItem {
    private int itemId;
    private String name;

    /* Constructor */
    public InventoryItem(int itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    /* Getters and Setters */
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* Abstract method to get item details */
    public abstract String getDetails();
}
