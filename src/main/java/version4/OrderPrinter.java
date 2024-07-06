package main.java.version4;

import java.util.List;

public class OrderPrinter {
    public void printOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println("Session ID: " + order.getSessionId());
            System.out.println("Item: " + order.getItem());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Time Taken: " + order.getTimeTaken() + " minutes");
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("Unit Price: " + order.getUnitPrice());
            System.out.println("------------------------");
        }
    }
}