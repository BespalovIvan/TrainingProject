package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.repository.DBFileRepo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFileRepoImpl implements DBFileRepo {
    private final JDBCConnect jdbcConnect;

    public DBFileRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public byte[] getFileById(Long fileId) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT data FROM files WHERE id = ?");
            preparedStatement.setLong(1, fileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                InputStream is = resultSet.getBinaryStream(1);
                return is.readAllBytes();
            } else {
                throw new RuntimeException("file not found");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
