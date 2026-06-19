package Stock_Trading_Platform.src.dao;

import Stock_Trading_Platform.src.database.DatabaseConnection;
import Stock_Trading_Platform.src.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public void saveTransaction(Transaction transaction){
        String sql="insert into transactions(user_id,stock_id,transaction_type,quantity,stock_price) values(?,?,?,?,?)";
        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,transaction.getUserId());
            ps.setInt(2,transaction.getStockId());
            ps.setString(3,transaction.getTransactionType());
            ps.setInt(4,transaction.getQuantity());
            ps.setDouble(5,transaction.getTradePrice());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<Transaction> getTransactionsByUser(int userId){

        String  sql="select * from transactions where user_id=?";
        List<Transaction> t=new ArrayList<>();
        try(Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,userId);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Transaction transaction=new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setStockId(rs.getInt("stock_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setQuantity(rs.getInt("quantity"));
                transaction.setTradePrice(rs.getDouble("stock_price"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());

                t.add(transaction);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  t;
    }
}
