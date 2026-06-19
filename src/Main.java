package Stock_Trading_Platform.src;

import Stock_Trading_Platform.src.dao.StockDAO;
import Stock_Trading_Platform.src.model.Portfolio;
import Stock_Trading_Platform.src.model.Stock;
import Stock_Trading_Platform.src.model.Transaction;
import Stock_Trading_Platform.src.service.TradingService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TradingService tradingService = new TradingService();
        StockDAO stockDAO = new StockDAO();

        while(true){

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. View Profit/Loss");
            System.out.println("7. Exit");

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    //view market
                    System.out.println("==================MARKET================");
                    System.out.printf("%-10s %-20s %-20s %-15s\n","STOCK ID","STOCK NAME","AVAILABLE QUANTITY","PRICE");
                    for( Stock stock:stockDAO.getAllStocks()){
                        System.out.printf("%-10s %-20s %-20s %-15s\n",stock.getStockId(),stock.getStockName(),stock.getAvailableQuantity(),stock.getCurrentPrice());
                    }

                    break;

                case 2:
                    // Buy Stock
                    System.out.print("Enter user ID:");
                    int userId=sc.nextInt();

                    System.out.print("Enter Stock ID:");
                    int stockId=sc.nextInt();

                    System.out.print("Enter Quantity:");
                    int quantity=sc.nextInt();

                    TransactionStatus status=tradingService.buyStock(userId,stockId,quantity);

                    switch(status){

                        case SUCCESS ->
                                System.out.println(
                                        "Stock Purchased Successfully."
                                );

                        case USER_NOT_FOUND ->
                                System.out.println(
                                        "User Not Found."
                                );

                        case STOCK_NOT_FOUND ->
                                System.out.println(
                                        "Stock Not Found."
                                );

                        case INSUFFICIENT_BALANCE ->
                                System.out.println(
                                        "Insufficient Wallet Balance."
                                );

                        case INSUFFICIENT_STOCK ->
                                System.out.println(
                                        "Required Stock Quantity Not Available."
                                );

                        case INVALID_QUANTITY ->
                                System.out.println(
                                        "Invalid Quantity."
                                );
                    }
                    break;

                case 3:
                    // Sell Stock
                    System.out.print("Enter user ID:");
                    int userId1=sc.nextInt();

                    System.out.print("Enter Stock ID:");
                    int stockId1=sc.nextInt();

                    System.out.print("Enter Quantity:");
                    int quantity1=sc.nextInt();

                    TransactionStatus status1=tradingService.sellStock(userId1,stockId1,quantity1);

                    switch(status1){

                        case SUCCESS ->
                                System.out.println(
                                        "Stock Sold Successfully."
                                );

                        case USER_NOT_FOUND ->
                                System.out.println(
                                        "User Not Found."
                                );

                        case STOCK_NOT_FOUND ->
                                System.out.println(
                                        "Stock Not Found."
                                );

                        case INSUFFICIENT_BALANCE ->
                                System.out.println(
                                        "Insufficient Wallet Balance."
                                );

                        case INSUFFICIENT_STOCK ->
                                System.out.println(
                                        "Required Stock Quantity Not Available."
                                );

                        case INVALID_QUANTITY ->
                                System.out.println(
                                        "Invalid Quantity."
                                );
                    }
                    break;
                case 4:
                    // View Portfolio

                    System.out.print("Enter user ID: ");
                    int userid2=sc.nextInt();
                    List<Portfolio> portfolios=tradingService.viewPortfolio(userid2);
                    if(portfolios == null){
                        System.out.println("User Not Found");
                        break;
                    }
                    System.out.printf("%-10s %-20s %-10s %-20s %-20S\n","STOCK ID","STOCK NAME","QUANTITY","PURCHASED PRICE","CURRENT PRICE");
                    for(Portfolio portfolio:portfolios){
                        Stock stock=stockDAO.getStockById(portfolio.getStockId());
                        System.out.printf("%-10s %-20s %-10s %-20s %-20s\n",portfolio.getStockId(),stock.getStockName(),portfolio.getQuantity(),portfolio.getPurchasePrice(),stock.getCurrentPrice());
                    }
                    break;

                case 5:
                    // View Transaction History
                    System.out.print("Enter user ID: ");
                    int userid3=sc.nextInt();
                    List<Transaction> transactions=tradingService.viewTransactionHistory(userid3);
                    if(transactions == null){
                        System.out.println("User Not Found");
                        break;
                    }
                    System.out.printf("%-20s %-10s %-20s %-20s %-20S %-30s \n","TRANSACTION ID","STOCK ID","TRANSACTION TYPE","QUANTITY","TRADE PRICE","TRANSACTION DATE");
                    for(Transaction transaction:transactions){

                        System.out.printf("%-20s %-10s %-20s %-20s %-20s %-30s\n",transaction.getTransactionId(),transaction.getStockId(),transaction.getTransactionType(),transaction.getQuantity(),transaction.getTradePrice(),transaction.getTransactionDate());
                    }
                    break;

                case 6:
                    // View Profit/Loss
                    System.out.print("Enter user ID :");
                    int userid4=sc.nextInt();

                    double amount=tradingService.viewProfitLoss(userid4);
                    if(amount<0) {
                        System.out.println("Loss :"+amount);
                    }else if(amount==0){
                        System.out.println("No Loss or Profit ");
                    }else{
                        System.out.println("Profit :"+amount);
                    }

                    break;

                case 7:
                    System.out.println("Thank You...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }
}
