package com.example.trainingProject.config;

import java.sql.Connection;

public interface JDBCConnect {
    public Connection createConnection();
}
