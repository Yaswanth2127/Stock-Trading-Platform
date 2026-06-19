package Stock_Trading_Platform.src.dao;

import Stock_Trading_Platform.src.database.DatabaseConnection;
import Stock_Trading_Platform.src.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
    public User getUserById(int userId){
        String  sql="select * from users where user_id=?";

        try(Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setWalletBalance(rs.getDouble("wallet_balance"));

                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateWalletBalance(int userId,double balance){
        String sql="update users set wallet_balance=? where user_id=?";

        try(Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(2,userId);
            ps.setDouble(1,balance);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addUser(User user){
        String sql="insert into users (user_name,wallet_balance) values (?,?);";

        try(Connection con= DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)
        ){
            ps.setDouble(2,user.getWalletBalance());
            ps.setString(1, user.getUserName());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
