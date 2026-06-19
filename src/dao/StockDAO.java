package Stock_Trading_Platform.src.dao;

import Stock_Trading_Platform.src.database.DatabaseConnection;
import Stock_Trading_Platform.src.model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    public  void addStock(Stock stock){
        // sql statement
        String sql="insert into stocks(stock_name,current_price,available_quantity) values(?,?,?)";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, stock.getStockName());
            ps.setDouble(2, stock.getCurrentPrice());
            ps.setInt(3, stock.getAvailableQuantity());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace();
        }
    }
    public List<Stock> getAllStocks(){
        String sql="select * from stocks";
        List<Stock> stocks = new ArrayList<>();
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setStockId(rs.getInt("stock_id"));
                stock.setStockName(rs.getString("stock_name"));
                stock.setCurrentPrice(rs.getDouble("current_price"));
                stock.setAvailableQuantity( rs.getInt("available_quantity") );
                stocks.add(stock);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return stocks;


    }

    public Stock getStockById(int stockId){
        String sql="select * from stocks where stock_id=?";
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stockId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Stock stock = new Stock();
                stock.setStockId(rs.getInt("stock_id"));
                stock.setStockName(rs.getString("stock_name"));
                stock.setCurrentPrice(rs.getDouble("current_price"));
                stock.setAvailableQuantity( rs.getInt("available_quantity") );
                return stock;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void updateStockPrice(int stockId,double newPrice){
        String sql="update stocks set current_price=? where stock_id=?";

        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)
        ){
            ps.setDouble(1,newPrice);
            ps.setInt(2,stockId);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateAvailableQuantity(int stockId,int quantity){
        String sql="update stocks set available_quantity =? where stock_id=?";
        try(Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,quantity);
            ps.setInt(2,stockId);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
