package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.Product;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.OrderRepo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepoImpl implements OrderRepo {
    private final JDBCConnect jdbcConnect;

    public OrderRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Order> findBetween(Long with, Long by) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.id as order_id, o.user_id,u.name,u.email," +
                    " o.datetime as date, op.product_id , op.product_quantity, p.name as product_name, p.price " +
                    "FROM orders as o " +
                    "JOIN order_products as op ON (o.id = op.order_id) " +
                    "JOIN products as p ON (op.product_id = p.id) " +
                    "JOIN users as u ON (o.user_id = u.id) WHERE order_id BETWEEN ? AND ?");
            preparedStatement.setLong(1, with);
            preparedStatement.setLong(2, by);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (orders.isEmpty()) {
                    List<Product> productList = new ArrayList<>();
                    productList.add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                    orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                            , resultSet.getString(3), resultSet.getString(4)), productList,
                            resultSet.getTimestamp(5).toLocalDateTime()));
                } else {
                    Order o = orders.get(orders.size() - 1);
                    if (o.getId() != resultSet.getLong(1)) {
                        List<Product> productList = new ArrayList<>();
                        productList.add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                        orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                                , resultSet.getString(3), resultSet.getString(4)), productList,
                                resultSet.getTimestamp(5).toLocalDateTime()));
                    } else {
                        o.getProducts().add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                    }
                }
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }

    }

    @Override
    public Optional<Order> findById(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.id as order_id, " +
                    "o.user_id,u.name,u.email,o.datetime as date, op.product_id , op.product_quantity," +
                    " p.name as product_name, p.price " +
                    "FROM orders as o JOIN order_products as op ON (o.id = op.order_id) " +
                    "JOIN products as p ON (op.product_id = p.id) " +
                    "JOIN users as u ON (o.user_id = u.id) WHERE order_id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            Order result = null;
            while (resultSet.next()) {
                if (productList.isEmpty()) {
                    productList.add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                    result = new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                            , resultSet.getString(3), resultSet.getString(4)),
                            productList, resultSet.getTimestamp(5).toLocalDateTime());
                } else {
                    result.getProducts().add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                }
            }
            return Optional.ofNullable(result);

        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.id as order_id, " +
                    "o.user_id,u.name,u.email, o.datetime as date, op.product_id , " +
                    "op.product_quantity, p.name as product_name, p.price " +
                    "FROM orders as o JOIN order_products as op ON (o.id = op.order_id) " +
                    "JOIN products as p ON (op.product_id = p.id) JOIN users as u ON (o.user_id = u.id) " +
                    "WHERE user_id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (orders.isEmpty()) {
                    List<Product> productList = new ArrayList<>();
                    productList.add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                    orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                            , resultSet.getString(3), resultSet.getString(4)), productList,
                            resultSet.getTimestamp(5).toLocalDateTime()));
                } else {
                    Order o = orders.get(orders.size() - 1);
                    if (o.getId() != resultSet.getLong(1)) {
                        List<Product> productList = new ArrayList<>();
                        productList.add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                        orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                                , resultSet.getString(3), resultSet.getString(4)), productList,
                                resultSet.getTimestamp(5).toLocalDateTime()));
                    } else {
                        o.getProducts().add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                    }
                }
            }
            return orders;

        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Long createOrder(Long userId, LocalDateTime dateTime, Long productId, Integer countProducts) {
        try (Connection connection = jdbcConnect.createConnection()) {
            connection.setAutoCommit(false);
            Long orderId = createOrder(userId, dateTime, connection);
            createOrderProduct(productId, countProducts, connection, orderId);
            connection.commit();
            System.out.println(orderId);
            return orderId;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    private static void createOrderProduct(Long productId, Integer countProducts, Connection connection, Long order_id) {
        try {
            PreparedStatement insertToOrderProducts = connection.prepareStatement("INSERT INTO order_products " +
                    "(order_id,product_id,product_quantity)" +
                    "VALUES (?,?,?);");
            insertToOrderProducts.setLong(1, order_id);
            insertToOrderProducts.setLong(2, productId);
            insertToOrderProducts.setInt(3, countProducts);
            insertToOrderProducts.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }


    private static Long createOrder(Long userId, LocalDateTime dateTime, Connection connection) {
        try {
            PreparedStatement insertToOrders = connection.prepareStatement(
                    "INSERT INTO orders (user_id,datetime)" +
                            "VALUES (?,?)", new String[]{"id"});
            insertToOrders.setLong(1, userId);
            insertToOrders.setTimestamp(2, Timestamp.valueOf(dateTime));
            insertToOrders.executeUpdate();
            ResultSet gk = insertToOrders.getGeneratedKeys();
            if (gk.next()) {
                return gk.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
        throw new RuntimeException("Failed to create order");
    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
