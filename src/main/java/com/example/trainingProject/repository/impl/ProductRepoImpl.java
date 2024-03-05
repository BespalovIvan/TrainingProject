package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderProduct;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.repository.ProductRepo;
import org.springframework.stereotype.Repository;

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
    public List<OrderProduct> findProductByOrderId(Order order) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT op.order_id, op.product_id," +
                    "p.title,p.price,SUM(p.price),COUNT(*) FROM order_products as op JOIN products as p ON (op.product_id = p.id) " +
                    "WHERE op.order_id = ? GROUP BY p.price, op.product_id,p.title,op.order_id");
            preparedStatement.setLong(1, order.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(new OrderProduct(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getBigDecimal(4), resultSet.getString(3),
                        resultSet.getBigDecimal(5), resultSet.getInt(6), order.getStatus()));
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
    public void deleteProductFromOrder(Long orderId, Long productId) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM order_products WHERE id = ANY (SELECT id FROM order_products " +
                            "WHERE order_id = ? AND product_id = ? LIMIT 1)");
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }
}
