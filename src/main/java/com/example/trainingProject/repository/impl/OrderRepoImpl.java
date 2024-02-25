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
import java.util.Optional;

@Repository
public class OrderRepoImpl implements OrderRepo {
    private final JDBCConnect jdbcConnect;

    public OrderRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getInt(3), resultSet.getTimestamp(4).toLocalDateTime(),
                        resultSet.getTimestamp(5).toLocalDateTime(),
                        OrderStatus.valueOf(resultSet.getString(6))));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Optional<Order> findNewOrderByUserId(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM orders WHERE user_id = ? AND status = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, "NEW");
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Order> orderOptional = Optional.empty();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getInt(3), resultSet.getTimestamp(4).toLocalDateTime(),
                        resultSet.getTimestamp(5).toLocalDateTime(),
                        OrderStatus.valueOf(resultSet.getString(6)));
                orderOptional = Optional.of(order);

            }
            return orderOptional;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public Order createOrder(Long userId) {
        Order order = new Order(userId, 0, LocalDateTime.now(), LocalDateTime.now(), OrderStatus.NEW);
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO orders (user_id,total_cost,create_date_time," +
                            "update_date_time,status) VALUES (?,?,?,?,?)", new String[]{"id"});
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setInt(2, order.getTotalCost());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getOrderCreationDate()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getOrderUpdateDate()));
            preparedStatement.setString(5, order.getStatus().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long orderId = generatedKeys.getLong("id");
                order.setId(orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
        return order;

    }


    @Override
    public void deleteOrder(Integer id) {

    }
}
