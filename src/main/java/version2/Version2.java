package main.java.version2;

import java.io.IOException;
import java.util.List;

public class Version2 {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv";
        FileReader fileReader = new FileReader(filePath);
        OrderParser orderParser = new OrderParser();
        OrderPrinter orderPrinter = new OrderPrinter();

        try {
            List<String> orderLines = fileReader.readLines();
            List<Order> orders = orderParser.parseOrders(orderLines);
            System.out.println("Order Details:");
            orderPrinter.printOrders(orders);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}