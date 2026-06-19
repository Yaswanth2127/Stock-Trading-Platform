package Stock_Trading_Platform.src.model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private int userId;
    private int stockId;
    private String transactionType;
    private int quantity;
    private double tradePrice;
    private LocalDateTime transactionDate;

    public Transaction(int userId, int stockId, String transactionType, int quantity, double tradePrice) {
        this.userId = userId;
        this.stockId = stockId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }
    public Transaction(){

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
