package main.java.version1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Version1 {
    private String filePath;

    public Version1(String filePath) {
        this.filePath = filePath;
    }

    public void processOrders() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            boolean isFirstLine = true;
            System.out.println("Order Details:");
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header row
                }

                String[] orderDetails = line.split(",");
                if (orderDetails.length == 6) {
                    int sessionId = Integer.parseInt(orderDetails[0]);
                    String item = orderDetails[1];
                    int quantity = Integer.parseInt(orderDetails[2]);
                    int timeTaken = Integer.parseInt(orderDetails[3]);
                    double totalPrice = Double.parseDouble(orderDetails[4]);
                    double unitPrice = Double.parseDouble(orderDetails[5]);

                    // Print the order details
                    System.out.println("Session ID: " + sessionId);
                    System.out.println("Item: " + item);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Time Taken: " + timeTaken + " minutes");
                    System.out.println("Total Price: " + totalPrice);
                    System.out.println("Unit Price: " + unitPrice);
                    System.out.println("------------------------");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Version1 processor = new Version1("src/main/resources/data.csv");
        processor.processOrders();
    }
}