package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.DBFile;
import com.example.trainingProject.repository.DBFileRepo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DBFileRepoImpl implements DBFileRepo {
    private final JDBCConnect jdbcConnect;

    public DBFileRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public DBFile getFileByProductId(Long productId) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM files WHERE product_id = ?");
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                InputStream is = resultSet.getBinaryStream(4);
                return new DBFile(resultSet.getLong(1), resultSet.getString(3), is.readAllBytes());
            } else {
                throw new RuntimeException("file not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("problem with database query");
        } catch (IOException e) {
            throw new RuntimeException("problem reading file");
        }
    }
}
