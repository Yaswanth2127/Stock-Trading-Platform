package Stock_Trading_Platform.src.dao;
import Stock_Trading_Platform.src.database.DatabaseConnection;
import Stock_Trading_Platform.src.model.Portfolio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PortfolioDAO {
    public void addHolding(Portfolio portfolio){

        String sql="insert into portifolio(user_id,stock_id,quantity,purchase_price) values(?,?,?,?)";

        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,portfolio.getUserId());
            ps.setInt(2,portfolio.getStockId());
            ps.setInt(3,portfolio.getQuantity());
            ps.setDouble(4,portfolio.getPurchasePrice());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public Portfolio getHolding(int userId,int stockId){

        String  sql="select * from portifolio where user_id=? and stock_id =?";

        try(Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1,userId);
            ps.setInt(2,stockId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Portfolio portfolio=new Portfolio();
                portfolio.setPortfolioId(rs.getInt("portifolio_id"));
                portfolio.setUserId(rs.getInt("user_id"));
                portfolio.setStockId(rs.getInt("stock_id"));
                portfolio.setQuantity(rs.getInt("quantity"));
                portfolio.setPurchasePrice(rs.getDouble("purchase_price"));
                return  portfolio;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;

    }

    public List<Portfolio> getPortfolioByUser(int userId){
        String sql="select * from portifolio where user_id=?";
        List<Portfolio> p=new ArrayList<>();

        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){

            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                Portfolio portfolio=new Portfolio();
                portfolio.setPortfolioId(rs.getInt("portifolio_id"));
                portfolio.setUserId(rs.getInt("user_id"));
                portfolio.setStockId(rs.getInt("stock_id"));
                portfolio.setQuantity(rs.getInt("quantity"));
                portfolio.setPurchasePrice(rs.getDouble("purchase_price"));
                p.add(portfolio);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    public void updateHoldingQuantity(int portfolioId,int quantity){
        String sql="update portifolio set quantity =? where portifolio_id=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1,quantity);
            ps.setInt(2,portfolioId);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
