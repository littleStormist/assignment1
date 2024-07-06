package main.java.version4;

import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(List<String> orderLines);
}