package inventory.client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManaging {
    private final String filePath = "orders.txt";

    /* Add a new order */
    public void addOrder(int orderId, String productName, int quantity, String orderDate, String status) {
        Order order = new Order(orderId, productName, quantity, orderDate, status);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(order.getItemId() + "," + order.getName() + "," + order.getQuantity() + "," +
                         order.getOrderDate() + "," + order.getStatus() + "\n");
            System.out.println("Order added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding order: " + e.getMessage());
        }
    }

    /* Update order details */
    public void updateOrder(int orderId, int newQuantity, String newStatus) {
        List<Order> orders = getAllOrders();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Order order : orders) {
                if (order.getItemId() == orderId) {
                    order.setQuantity(newQuantity);
                    order.setStatus(newStatus);
                    found = true;
                }
                writer.write(order.getItemId() + "," + order.getName() + "," + order.getQuantity() + "," +
                             order.getOrderDate() + "," + order.getStatus() + "\n");
            }
            if (found) {
                System.out.println("Order updated successfully!");
            } else {
                System.out.println("Order not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while updating order: " + e.getMessage());
        }
    }

    /* Delete an order */
    public void deleteOrder(int orderId) {
        List<Order> orders = getAllOrders();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Order order : orders) {
                if (order.getItemId() == orderId) {
                    found = true;
                    continue;
                }
                writer.write(order.getItemId() + "," + order.getName() + "," + order.getQuantity() + "," +
                             order.getOrderDate() + "," + order.getStatus() + "\n");
            }
            if (found) {
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("Order not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while deleting order: " + e.getMessage());
        }
    }

    /* Retrieve all orders */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                int orderId = Integer.parseInt(parts[0]);
                String productName = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                String orderDate = parts[3];
                String status = parts[4];
                orders.add(new Order(orderId, productName, quantity, orderDate, status));
            }
        } catch (IOException e) {
            System.out.println("Error while reading orders: " + e.getMessage());
        }
        return orders;
    }
}
