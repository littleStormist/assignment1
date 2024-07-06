package main.java.version4;

public class Order {
    private int sessionId;
    private String item;
    private int quantity;
    private int timeTaken;
    private double totalPrice;
    private double unitPrice;

    public Order(int sessionId, String item, int quantity, int timeTaken, double totalPrice, double unitPrice) {
        this.sessionId = sessionId;
        this.item = item;
        this.quantity = quantity;
        this.timeTaken = timeTaken;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    // Getters for the order properties
    public int getSessionId() {
        return sessionId;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}