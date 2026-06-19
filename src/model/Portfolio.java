package Stock_Trading_Platform.src.model;

public class Portfolio {
    private int portfolioId;
    private int userId;
    private int stockId;
    private int quantity;
    private double purchasePrice;

    public Portfolio(){

    }
    public Portfolio(int userId, int stockId, int quantity, double purchasePrice) {
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
