package main.java.version3;

import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(List<String> orderLines);
}