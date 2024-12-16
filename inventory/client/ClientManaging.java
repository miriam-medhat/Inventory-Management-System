package inventory.client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientManaging {
    private final String filePath = "clients.txt";

    /* Add a new client */
    public void addClient(int clientId, String name, String contactInfo, String address) {
        Client client = new Client(clientId, name, contactInfo, address);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(client.getItemId() + "," + client.getName() + "," + client.getContactInfo() + "," + client.getAddress() + "\n");
            System.out.println("Client added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding client: " + e.getMessage());
        }
    }

    /* Update client details */
    public void updateClient(int clientId, String newContactInfo, String newAddress) {
        List<Client> clients = getAllClients();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Client client : clients) {
                if (client.getItemId() == clientId) {
                    client.setContactInfo(newContactInfo);
                    client.setAddress(newAddress);
                    found = true;
                }
                writer.write(client.getItemId() + "," + client.getName() + "," + client.getContactInfo() + "," + client.getAddress() + "\n");
            }
            if (found) {
                System.out.println("Client updated successfully!");
            } else {
                System.out.println("Client not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while updating client: " + e.getMessage());
        }
    }

    /* Delete a client */
    public void deleteClient(int clientId) {
        List<Client> clients = getAllClients();
        boolean found = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Client client : clients) {
                if (client.getItemId() == clientId) {
                    found = true;
                    continue;
                }
                writer.write(client.getItemId() + "," + client.getName() + "," + client.getContactInfo() + "," + client.getAddress() + "\n");
            }
            if (found) {
                System.out.println("Client deleted successfully!");
            } else {
                System.out.println("Client not found!");
            }
        } catch (IOException e) {
            System.out.println("Error while deleting client: " + e.getMessage());
        }
    }

    /* Retrieve all clients */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                int clientId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String contactInfo = parts[2];
                String address = parts[3];
                clients.add(new Client(clientId, name, contactInfo, address));
            }
        } catch (IOException e) {
            System.out.println("Error while reading clients: " + e.getMessage());
        }
        return clients;
    }
}
