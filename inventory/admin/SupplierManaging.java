package inventory.admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierManaging {
    private final String filePath = "suppliers.txt";

    /* Add a new supplier */
    public void addSupplier(int supplierId, String name, String contactInfo) {
        Supplier supplier = new Supplier(supplierId, name, contactInfo);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(supplier.getItemId() + "," + supplier.getName() + "," + supplier.getContactInfo() + "\n");
            System.out.println("Supplier added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding supplier: " + e.getMessage());
        }
    }

    /* Update supplier details */
    public void updateSupplier(int supplierId, String newName, String newContactInfo) {
        List<Supplier> suppliers = getAllSuppliers();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Supplier supplier : suppliers) {
                if (supplier.getItemId() == supplierId) {
                    supplier.setName(newName);
                    supplier.setContactInfo(newContactInfo);
                    found = true;
                }
                writer.write(supplier.getItemId() + "," + supplier.getName() + "," + supplier.getContactInfo() + "\n");
            }
            if (found) {
                System.out.println("Supplier updated successfully!");
            } else {
                System.out.println("Supplier not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while updating supplier: " + e.getMessage());
        }
    }

    /* Delete a supplier */
    public void deleteSupplier(int supplierId) {
        List<Supplier> suppliers = getAllSuppliers();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Supplier supplier : suppliers) {
                if (supplier.getItemId() == supplierId) {
                    found = true;
                    continue;
                }
                writer.write(supplier.getItemId() + "," + supplier.getName() + "," + supplier.getContactInfo() + "\n");
            }
            if (found) {
                System.out.println("Supplier deleted successfully!");
            } else {
                System.out.println("Supplier not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while deleting supplier: " + e.getMessage());
        }
    }

    /* Retrieve all suppliers */
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                int supplierId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String contactInfo = parts[2];
                suppliers.add(new Supplier(supplierId, name, contactInfo));
            }
        } catch (IOException e) {
            System.out.println("Error while reading suppliers: " + e.getMessage());
        }
        return suppliers;
    }
}
