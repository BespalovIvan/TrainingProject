package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.ProductRepo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepoImpl implements ProductRepo {

    private final JDBCConnect jdbcConnect;

    public ProductRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                products.add(new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3), resultSet.getTimestamp(4).toLocalDateTime()));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public List<Product> findProductByOrderId(Long orderId) {
        List<Product> orderProducts = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT op.order_id,op.product_id," +
                    "p.title,p.price,op.added_date_time FROM order_products as op " +
                    "JOIN products as p ON op.product_id=p.id WHERE order_id = ?");
            preparedStatement.setLong(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(new Product(resultSet.getLong(2), resultSet.getString(3),
                        resultSet.getBigDecimal(4), resultSet.getTimestamp(5).toLocalDateTime()));
            }
            return orderProducts;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Product> optionalProduct = Optional.empty();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getBigDecimal(3), resultSet.getTimestamp(4).toLocalDateTime());
                optionalProduct = Optional.of(product);
            }
            return optionalProduct;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    //    @Override
//    public List<Product> findBetween(Long with, Long by) {
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = jdbcConnect.createConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products " +
//                    "WHERE id BETWEEN ? AND ?");
//            preparedStatement.setLong(1, with);
//            preparedStatement.setLong(2, by);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                products.add(new Product(resultSet.getLong(1),
//                        resultSet.getString(2),
//                        resultSet.getBigDecimal(3)));
//            }
//            return products;
//        } catch (SQLException e) {
//            throw new RuntimeException("invalid request");
//        }
//    }

//    @Override
//    public Optional<Product> findById(Long id) {
//        try (Connection connection = jdbcConnect.createConnection()) {
//            PreparedStatement preparedStatement = connection
//                    .prepareStatement("SELECT * FROM products WHERE id = ?");
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                Product result = new Product(resultSet.getLong(1)
//                        , resultSet.getString(2)
//                        , resultSet.getBigDecimal(3));
//                return Optional.of(result);
//            } else return Optional.empty();
//        } catch (SQLException e) {
//            throw new RuntimeException("invalid request");
//        }
//    }

    @Override
    public Product addProductToOrder(Long productId, Long orderId) {
        Product product = findById(productId).get();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO order_products (order_id,product_id,added_date_time) " +
                            "VALUES (?,?,?)");
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, product.getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Long updateProduct(Long id, String name, BigDecimal price) {
        try (Connection connection = jdbcConnect.createConnection()) {
            Long productId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?", new String[]{"id"});
            preparedStatement.setString(1, name);
            preparedStatement.setBigDecimal(2, price);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                productId = generatedKeys.getLong("id");
            }
            System.out.println(productId);
            return productId;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }

    }

    @Override
    public void deleteProductById(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }
}
