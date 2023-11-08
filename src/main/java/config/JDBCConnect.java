package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
    private static final String url = "jdbc:postgresql://localhost:5433/testdatabase";
    private static final String login = "postgres";
    private static final String password = "2208";

    public Connection getPostgresConnection(){
        try {
            return DriverManager.getConnection(url,login,password);
        } catch (SQLException e) {
            throw new RuntimeException("failed to create connection");
        }
    }
}
