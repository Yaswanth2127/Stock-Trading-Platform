package Stock_Trading_Platform.src.service;

import Stock_Trading_Platform.src.TransactionStatus;
import Stock_Trading_Platform.src.dao.PortfolioDAO;
import Stock_Trading_Platform.src.dao.StockDAO;
import Stock_Trading_Platform.src.dao.TransactionDAO;
import Stock_Trading_Platform.src.dao.UserDAO;
import Stock_Trading_Platform.src.model.Portfolio;
import Stock_Trading_Platform.src.model.Stock;
import Stock_Trading_Platform.src.model.Transaction;
import Stock_Trading_Platform.src.model.User;

import java.util.List;

public class TradingService {
    UserDAO userDAO=new UserDAO();
    TransactionDAO transactionDAO=new TransactionDAO();
    StockDAO stockDAO=new StockDAO();
    PortfolioDAO portfolioDAO=new PortfolioDAO();
    public TransactionStatus buyStock(int userId, int stockId, int quantity){
        if(quantity<=0){
            return TransactionStatus.INVALID_QUANTITY;
        }
        User user=userDAO.getUserById(userId);
        if(user==null){
            return TransactionStatus.USER_NOT_FOUND;
        }
        Stock stock= stockDAO.getStockById(stockId);
        if(stock==null){
            return TransactionStatus.STOCK_NOT_FOUND;
        }

        if(stock.getAvailableQuantity()<quantity){
            return TransactionStatus.INSUFFICIENT_STOCK;
        }

        double totalCost = quantity * stock.getCurrentPrice();
        if(user.getWalletBalance()<totalCost){
            return TransactionStatus.INSUFFICIENT_BALANCE;
        }

        stockDAO.updateAvailableQuantity(stockId,stock.getAvailableQuantity()-quantity);
        double balance=user.getWalletBalance()-totalCost;
        userDAO.updateWalletBalance(userId,balance);

        Portfolio portfolio=portfolioDAO.getHolding(userId,stockId);
        if(portfolio==null){
            portfolioDAO.addHolding(new Portfolio(userId,stockId,quantity,stock.getCurrentPrice()));
        }else{
            portfolioDAO.updateHoldingQuantity(portfolio.getPortfolioId(),portfolio.getQuantity()+quantity);
        }


        transactionDAO.saveTransaction(new Transaction(userId,stockId,"BUY",quantity,stock.getCurrentPrice()));

        return TransactionStatus.SUCCESS;

    }

    public  TransactionStatus sellStock(int userId,int stockId,int quantity){
        if(quantity<=0 ){
            return TransactionStatus.INVALID_QUANTITY;
        }
        User user=userDAO.getUserById(userId);
        if(user==null){
            return TransactionStatus.USER_NOT_FOUND;
        }
        Stock stock=stockDAO.getStockById(stockId);
        if(stock==null){
            return TransactionStatus.STOCK_NOT_FOUND;
        }
        Portfolio portfolio=portfolioDAO.getHolding(userId,stockId);
        if(portfolio==null){
            return  TransactionStatus.PORTFOLIO_NOT_FOUND;
        }

        if(portfolio.getQuantity()<quantity){
            return TransactionStatus.INSUFFICIENT_SHARES;
        }

        double sellPrice=quantity*stock.getCurrentPrice();
        userDAO.updateWalletBalance(userId, user.getWalletBalance()+sellPrice);


        int q=stock.getAvailableQuantity()+quantity;
        stockDAO.updateAvailableQuantity(stockId,q);

        int rem=portfolio.getQuantity()-quantity;
        portfolioDAO.updateHoldingQuantity(portfolio.getPortfolioId(),rem);

        transactionDAO.saveTransaction(new Transaction(userId,stockId,"SELL",quantity,stock.getCurrentPrice()));


        return TransactionStatus.SUCCESS;
    }

    public List<Portfolio> viewPortfolio(int userId){
        User user=userDAO.getUserById(userId);
        if(user==null){
            return  null;
        }
        return portfolioDAO.getPortfolioByUser(userId);
    }

    public List<Transaction> viewTransactionHistory(int userId){

        User user=userDAO.getUserById(userId);
        if(user==null){

            return null;
        }
        return transactionDAO.getTransactionsByUser(userId);
    }

    public double viewProfitLoss(int userId){
        User user=userDAO.getUserById(userId);

        if(user==null){
            return 0.0;
        }
        List<Portfolio> portfolio=portfolioDAO.getPortfolioByUser(userId);
        if (portfolio==null || portfolio.isEmpty()){
            return 0.0;
        }
        double profit=0.0;

        for(Portfolio portfolio1:portfolio){
            int stockId=portfolio1.getStockId();
            Stock stock=stockDAO.getStockById(stockId);
            if(stock!=null){
                int quantity=portfolio1.getQuantity();
                double currentPrice=stock.getCurrentPrice();
                profit+=quantity*(currentPrice-portfolio1.getPurchasePrice());
            }
        }
        return profit;
    }
}
