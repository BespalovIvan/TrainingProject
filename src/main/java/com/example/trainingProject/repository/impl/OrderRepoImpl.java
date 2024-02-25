package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.Order;
import com.example.trainingProject.entity.OrderStatus;
import com.example.trainingProject.repository.OrderRepo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {
    private final JDBCConnect jdbcConnect;

    public OrderRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

//    @Override
//    public List<Order> findBetween(Long with, Long by) {
//        List<Order> orders = new ArrayList<>();
//        try (Connection connection = jdbcConnect.createConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.id as order_id, o.user_id,u.name,u.email," +
//                    " o.datetime as date, op.product_id , op.product_quantity, p.name as product_name, p.price " +
//                    "FROM orders as o " +
//                    "JOIN order_products as op ON (o.id = op.order_id) " +
//                    "JOIN products as p ON (op.product_id = p.id) " +
//                    "JOIN users as u ON (o.user_id = u.id) WHERE order_id BETWEEN ? AND ?");
//            preparedStatement.setLong(1, with);
//            preparedStatement.setLong(2, by);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                if (orders.isEmpty()) {
//                    List<Product> productList = new ArrayList<>();
//                    productList.add(new Product(resultSet.getLong(6),
//                            resultSet.getString(8), resultSet.getBigDecimal(9)));
//                    orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
//                            , resultSet.getString(3), resultSet.getString(4)), productList,
//                            resultSet.getTimestamp(5).toLocalDateTime()));
//                } else {
//                    Order o = orders.get(orders.size() - 1);
//                    if (o.getId() != resultSet.getLong(1)) {
//                        List<Product> productList = new ArrayList<>();
//                        productList.add(new Product(resultSet.getLong(6),
//                                resultSet.getString(8), resultSet.getBigDecimal(9)));
//                        orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
//                                , resultSet.getString(3), resultSet.getString(4)), productList,
//                                resultSet.getTimestamp(5).toLocalDateTime()));
//                    } else {
//                        o.getProducts().add(new Product(resultSet.getLong(6),
//                                resultSet.getString(8), resultSet.getBigDecimal(9)));
//                    }
//                }
//            }
//            return orders;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("invalid request", e);
//        }
//
//    }

//    @Override
//    public Optional<Order> findById(Long id) {
//        try (Connection connection = jdbcConnect.createConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.id as order_id, " +
//                    "o.user_id,u.name,u.email,o.datetime as date, op.product_id , op.product_quantity," +
//                    " p.name as product_name, p.price " +
//                    "FROM orders as o JOIN order_products as op ON (o.id = op.order_id) " +
//                    "JOIN products as p ON (op.product_id = p.id) " +
//                    "JOIN users as u ON (o.user_id = u.id) WHERE order_id = ?");
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Product> productList = new ArrayList<>();
//            Order result = null;
//            while (resultSet.next()) {
//                if (productList.isEmpty()) {
//                    productList.add(new Product(resultSet.getLong(6),
//                            resultSet.getString(8), resultSet.getBigDecimal(9)));
//                    result = new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
//                            , resultSet.getString(3), resultSet.getString(4)),
//                            productList, resultSet.getTimestamp(5).toLocalDateTime());
//                } else {
//                    result.getProducts().add(new Product(resultSet.getLong(6),
//                            resultSet.getString(8), resultSet.getBigDecimal(9)));
//                }
//            }
//            return Optional.ofNullable(result);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("invalid request", e);
//        }
//    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ? ");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getInt(3), resultSet.getTimestamp(4).toLocalDateTime(),
                        resultSet.getTimestamp(5).toLocalDateTime(),
                        OrderStatus.valueOf(resultSet.getString(6))));
            }
            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public Long createOrder(Long userId, Integer totalCost, LocalDateTime orderCreationDate,
                            LocalDateTime orderUpdateDate, OrderStatus status, Long productId) {
        List<Order> orders = findByUserId(userId);
        Long orderId = null;
        try (Connection connection = jdbcConnect.createConnection()) {
            connection.setAutoCommit(false);
            for (Order o : orders) {
                if (o.getStatus() != OrderStatus.NEW) {
                    orderId = orderCreate(userId, totalCost, orderCreationDate,
                            orderUpdateDate, OrderStatus.NEW, connection);
                    createOrderProduct(orderId, productId, orderUpdateDate, connection);
                } else {
                    createOrderProduct(o.getId(), productId, orderUpdateDate, connection);
                }
                connection.commit();

            }

            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    private static Long orderCreate(Long userId, Integer totalCost,
                                    LocalDateTime orderCreationDate, LocalDateTime orderUpdateDate,
                                    OrderStatus status, Connection connection) throws SQLException {
        PreparedStatement insertToOrders = connection.prepareStatement(
                "INSERT INTO orders (user_id,total_cost,create_date_time,update_date_time,status)" +
                        "VALUES (?,?,?,?,?)", new String[]{"id"});
        insertToOrders.setLong(1, userId);
        insertToOrders.setInt(2, totalCost);
        insertToOrders.setTimestamp(3, Timestamp.valueOf(orderCreationDate));
        insertToOrders.setTimestamp(4, Timestamp.valueOf(orderUpdateDate));
        insertToOrders.setString(5, status.name());
        insertToOrders.executeUpdate();
        Long orderId = null;
        ResultSet generatedKeys = insertToOrders.getGeneratedKeys();
        if (generatedKeys.next()) {
            orderId = generatedKeys.getLong("id");
        }
        return orderId;
    }

    private static void createOrderProduct(Long order_id, Long productId,
                                           LocalDateTime addedDateTime, Connection connection) {
        try {
            PreparedStatement insertToOrderProducts = connection.prepareStatement("INSERT INTO order_products " +
                    "(order_id,product_id,added_date_time)" +
                    "VALUES (?,?,?);");
            insertToOrderProducts.setLong(1, order_id);
            insertToOrderProducts.setLong(2, productId);
            insertToOrderProducts.setTimestamp(3, Timestamp.valueOf(addedDateTime));
            insertToOrderProducts.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
