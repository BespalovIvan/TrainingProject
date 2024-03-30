package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.DBFile;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.DBFileRepo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBFileRepoImpl implements DBFileRepo {
    private final JDBCConnect jdbcConnect;

    public DBFileRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<DBFile> getAllFiles() {
        List<DBFile> files = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM files");
            while (resultSet.next()){
                files.add(new DBFile(resultSet.getLong(1),resultSet.getString(2),
                        resultSet.getBytes(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return files;
    }

    @Override
    public byte[] getFileByProductId(Long productId) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT data FROM files" +
                    "WHERE product_id = ?");
            preparedStatement.setLong(1, productId);
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
