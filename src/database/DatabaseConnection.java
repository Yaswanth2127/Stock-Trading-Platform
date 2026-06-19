package Stock_Trading_Platform.src.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/stock_trading_platform";

    private static final String USERNAME = "springstudent";

    private static final String PASSWORD = "springstudent";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Database Connection Failed",
                    e
            );
        }
    }
}

