package main.java.version4;

import java.util.List;

public class Version4 {
    private OrderSource orderSource;
    private OrderParser orderParser;
    private OrderPrinter orderPrinter;

    public Version4(OrderSource orderSource, OrderParser orderParser, OrderPrinter orderPrinter) {
        this.orderSource = orderSource;
        this.orderParser = orderParser;
        this.orderPrinter = orderPrinter;
    }

    public void processOrders() {
        List<String> orderLines = orderSource.readOrderLines();
        List<Order> orders = orderParser.parseOrders(orderLines);
        System.out.println("Order Details:");
        orderPrinter.printOrders(orders);
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv";
        OrderSource orderSource = new FileOrderSource(filePath);
        OrderParser orderParser = new CSVOrderParser();
        OrderPrinter orderPrinter = new OrderPrinter();

        Version4 version4 = new Version4(orderSource, orderParser, orderPrinter);
        version4.processOrders();
    }
}