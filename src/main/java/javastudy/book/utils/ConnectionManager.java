package javastudy.book.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class ConnectionManager {

    //Constructor
    public static Connection getConnection() {

        Connection conn = null;
        String jdbcURL = "";
        String driver = "";
        String id = "";
        String password = "";

        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("DB"));

            jdbcURL = properties.getProperty("DB_URL");
            id = properties.getProperty("DB_USER");
            password = properties.getProperty("DB_PASSWORD");
            //JDBC Driver 등록
            Class.forName(properties.getProperty("DRIVER"));

            //연결하기
            conn = DriverManager.getConnection(
                jdbcURL,
                id,
                password

            );


        } catch (Exception e) {
            e.printStackTrace();

        }

        return conn;
    }
}
