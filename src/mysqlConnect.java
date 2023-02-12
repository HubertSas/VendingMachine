import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class mysqlConnect {
    private static String URL = "jdbc:mysql://localhost:3306/vendingmachine";
    private static String user = "root";
    private static String pass = "";

    public static Connection connect(){


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, user, pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }

        public static void close(Connection connection) {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}