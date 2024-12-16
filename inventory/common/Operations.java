package inventory.common;

import java.util.List;

public interface Operations<T extends InventoryItem> {
    void add(T item);

    void update(int id, T updatedItem);

    void delete(int id);

    T getById(int id);

    List<T> getAll();
}
