package inventory.admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManaging {
    private final String filePath = "categories.txt";

    /* Add a new category */
    public void addCategory(int categoryId, String name) {
        Category category = new Category(categoryId, name);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(category.getItemId() + "," + category.getName() + "\n");
            System.out.println("Category added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding category: " + e.getMessage());
        }
    }

    /* Update category details */
    public void updateCategory(int categoryId, String newName) {
        List<Category> categories = getAllCategories();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Category category : categories) {
                if (category.getItemId() == categoryId) {
                    category.setName(newName);
                    found = true;
                }
                writer.write(category.getItemId() + "," + category.getName() + "\n");
            }
            if (found) {
                System.out.println("Category updated successfully!");
            } else {
                System.out.println("Category not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while updating category: " + e.getMessage());
        }
    }

    /* Delete a category */
    public void deleteCategory(int categoryId) {
        List<Category> categories = getAllCategories();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Category category : categories) {
                if (category.getItemId() == categoryId) {
                    found = true;
                    continue;
                }
                writer.write(category.getItemId() + "," + category.getName() + "\n");
            }
            if (found) {
                System.out.println("Category deleted successfully!");
            } else {
                System.out.println("Category not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while deleting category: " + e.getMessage());
        }
    }

    /* Retrieve all categories */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                int categoryId = Integer.parseInt(parts[0]);
                String name = parts[1];
                categories.add(new Category(categoryId, name));
            }
        } catch (IOException e) {
            System.out.println("Error while reading categories: " + e.getMessage());
        }
        return categories;
    }
}
