package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.ProductRepo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepoImpl implements ProductRepo {

    private final JDBCConnect jdbcConnect;

    public ProductRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Product> findBetween(Long with, Long by) {
        List<Product> products = new ArrayList<>();
        try(Connection connection = jdbcConnect.createConnection()){
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products " +
                   "WHERE id BETWEEN ? AND ?");
           preparedStatement.setLong(1,with);
           preparedStatement.setLong(2,by);
           ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                products.add(new Product(resultSet.getLong(1),
                                      resultSet.getString(2),
                                      resultSet.getBigDecimal(3)));
            }
            return products;
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM products WHERE id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Product result = new Product(resultSet.getLong(1)
                        ,resultSet.getString(2)
                        ,resultSet.getBigDecimal(3));
                return Optional.of(result);
            }
            else return Optional.empty();
        }catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Long createProduct(String name, BigDecimal price) {
        try(Connection connection = jdbcConnect.createConnection()){
            Long productId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO products (name,price) VALUES (?,?)",new String[]{"id"});
            preparedStatement.setString(1,name);
            preparedStatement.setBigDecimal(2,price);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                productId = generatedKeys.getLong("id");
            }
            System.out.println(productId);
            return productId;
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Long updateProduct(Long id, String name, BigDecimal price) {
        try(Connection connection = jdbcConnect.createConnection()){
            Long productId = -1L;
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?", new String[]{"id"});
        preparedStatement.setString(1,name);
        preparedStatement.setBigDecimal(2,price);
        preparedStatement.setLong(3,id);
        preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                productId = generatedKeys.getLong("id");
            }
            System.out.println(productId);
            return productId;
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }

    }

    @Override
    public void deleteProductById(Long id) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }
}
