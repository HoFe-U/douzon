package javastudy.resultG;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public Connection getConnection() {
        Connection conn = null;
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("DB"));
            String url = properties.getProperty("DB_URL");
            String user = properties.getProperty("DB_USER");
            String pwd = properties.getProperty("DB_PASSWORD");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
