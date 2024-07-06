package main.java.version4;

import java.util.ArrayList;
import java.util.List;

public class CSVOrderParser implements OrderParser {
    @Override
    public List<Order> parseOrders(List<String> orderLines) {
        List<Order> orders = new ArrayList<>();
        boolean isFirstLine = true;
        for (String line : orderLines) {
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
                Order order = new Order(sessionId, item, quantity, timeTaken, totalPrice, unitPrice);
                orders.add(order);
            }
        }
        return orders;
    }
}