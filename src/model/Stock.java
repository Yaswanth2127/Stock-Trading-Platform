package Stock_Trading_Platform.src.model;

public class Stock {
    private int stockId;
    private String stockName;
    private  double currentPrice;
    private int availableQuantity;

    public Stock() {
    }

    public Stock(String stockName, double currentPrice, int availableQuantity) {
        this.stockName = stockName;
        this.currentPrice = currentPrice;
        this.availableQuantity = availableQuantity;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
