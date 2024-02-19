package com.example.trainingProject.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@Component
public class JDBCConnect {
    private final Properties properties;

    public JDBCConnect(Properties properties) {
        this.properties = properties;
    }

    public Connection createConnection() {
        Map<String,String> dataForConnect = properties.getProperties();
        try{
            return  DriverManager.getConnection(
                    dataForConnect.get("url"),
                    dataForConnect.get("login"),
                    dataForConnect.get("password"));
        } catch (SQLException e) {
            throw new RuntimeException("Failed create connection");
        }
    }
}
