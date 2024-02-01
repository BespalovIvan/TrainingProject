package repository.impl;

import config.JDBCConnect;
import entity.Product;
import repository.ProductRepo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {

    private final JDBCConnect jdbcConnect;

    public ProductRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = jdbcConnect.createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
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
    public Product findById(Long id) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM goods WHERE id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Product(resultSet.getLong(1)
                               ,resultSet.getString(2)
                               ,resultSet.getBigDecimal(3));
            }
            else throw new RuntimeException("product not found");
        }catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public void createProduct(String name, BigDecimal price) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO goods (name,price) VALUES (?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setBigDecimal(2,price);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public void updateProduct(Long id, String name, BigDecimal price) {
        try(Connection connection = jdbcConnect.createConnection()){
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE goods SET name = ?, price = ? WHERE id = ?");
        preparedStatement.setString(1,name);
        preparedStatement.setBigDecimal(2,price);
        preparedStatement.setLong(3,id);
        preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }

    }

    @Override
    public void deleteProductById(Long id) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM goods WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }

    }
}
