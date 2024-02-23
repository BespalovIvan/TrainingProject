package com.example.trainingProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
public class JDBCConnect {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String login;
    @Value("${spring.datasource.password}")
    String password;


    @Bean
    @Scope("prototype")
    public Connection createConnection() {

        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed create connection");
        }
    }
}
