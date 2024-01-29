package repository.impl;

import config.JDBCConnect;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import entity.User;
import repository.OrderRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepoImpl implements OrderRepo {
    private final JDBCConnect jdbcConnect;

    public OrderRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<Order> FindAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT o.id, s.name, u.first_name,u.last_name,p.name,create_ord " +
                    "FROM orders as o " +
                    "JOIN statuses s ON (o.status_id = s.id) " +
                    "JOIN users as u ON (o.user_id = u.id) " +
                    "JOIN products as p ON (o.product_id = p.id) ;");


            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Order findById(Integer id) {
        return null;
    }

    @Override
    public void createOrder(OrderStatus status, User user, List<Product> products, LocalDateTime createOrder) {

    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
