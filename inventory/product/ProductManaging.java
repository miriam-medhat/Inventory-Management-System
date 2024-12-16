package inventory.product;

import inventory.common.Operations;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManaging implements Operations<Product> {
    private static final String FILE_NAME = "products.txt";
    private List<Product> productList = new ArrayList<>();
    private static final int EXPIRATION_THRESHOLD_DAYS = 30; // 30 days threshold for near expiration
    private static final int LOW_STOCK_THRESHOLD = 10; // Threshold for low stock

    /* Load products from file */
    public void loadProductsFromFile() {
        productList.clear();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");
                if (details.length == 7) {
                    int productId = Integer.parseInt(details[0]);
                    String name = details[1];
                    String category = details[2];
                    String productionDate = details[3];
                    String expiryDate = details[4];
                    int quantity = Integer.parseInt(details[5]);
                    double price = Double.parseDouble(details[6]);
                    Product product = new Product(productId, name, category, productionDate, expiryDate, quantity, price);
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /* Implement the add method from Operations interface */
    @Override
    public void add(Product product) {
        productList.add(product);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(product.getItemId() + "," + product.getName() + "," + product.getCategory() + "," 
                        + product.getProductionDate() + "," + product.getExpirationDate() + "," 
                        + product.getQuantity() + "," + product.getPrice() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /* Implement the update method from Operations interface */
    @Override
    public void update(int id, Product updatedProduct) {
        boolean updated = false;

        for (Product product : productList) {
            if (product.getItemId() == id) {
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setProductionDate(updatedProduct.getProductionDate());
                product.setExpirationDate(updatedProduct.getExpirationDate());
                product.setQuantity(updatedProduct.getQuantity());
                product.setPrice(updatedProduct.getPrice());
                updated = true;
                break;
            }
        }

        if (updated) {
            saveAllProductsToFile();
        }
    }

    /* Implement the delete method from Operations interface */
    @Override
    public void delete(int id) {
        productList.removeIf(product -> product.getItemId() == id);
        saveAllProductsToFile();
    }

    /* Implement the getById method from Operations interface */
    @Override
    public Product getById(int id) {
        for (Product product : productList) {
            if (product.getItemId() == id) {
                return product;
            }
        }
        return null; // Return null if product not found
    }

    /* Implement the getAll method from Operations interface */
    @Override
    public List<Product> getAll() {
        return new ArrayList<>(productList);
    }

    /* Save all products to the file */
    private void saveAllProductsToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Product product : productList) {
                writer.write(product.getItemId() + "," + product.getName() + "," + product.getCategory() + ","
                        + product.getProductionDate() + "," + product.getExpirationDate() + ","
                        + product.getQuantity() + "," + product.getPrice() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /* Search by product production and expiration dates */
    public List<Product> searchByDates(String date) {
        List<Product> results = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Product product : productList) {
            // Compare the input date with both production and expiration dates
            LocalDate productionDate = LocalDate.parse(product.getProductionDate(), formatter);
            LocalDate expirationDate = LocalDate.parse(product.getExpirationDate(), formatter);
            LocalDate searchDate = LocalDate.parse(date, formatter);

            if (productionDate.isEqual(searchDate) || expirationDate.isEqual(searchDate)) {
                results.add(product);
            }
        }
        return results;
    }

    /* Notify about products near expiration */
    public List<Product> checkNearExpiration() {
        List<Product> expiringProducts = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Product product : productList) {
            LocalDate expirationDate = LocalDate.parse(product.getExpirationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (expirationDate.isBefore(currentDate.plusDays(EXPIRATION_THRESHOLD_DAYS))) {
                expiringProducts.add(product);
            }
        }
        return expiringProducts;
    }

    /* Notify about low stock products */
    public List<Product> checkLowStock() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getQuantity() <= LOW_STOCK_THRESHOLD) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
}
