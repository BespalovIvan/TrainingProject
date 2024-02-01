package repository.impl;

import config.JDBCConnect;
import entity.Order;
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
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT o.id as order_id, o.user_id,u.name,u.email," +
                    " o.datetime as date, op.product_id , op.product_quantity, p.name as product_name, p.price " +
                    "FROM orders as o " +
                    "JOIN order_products as op ON (o.id = op.order_id) " +
                    "JOIN products as p ON (op.product_id = p.id) " +
                    "JOIN users as u ON (o.user_id = u.id)");

            while (resultSet.next()) {
                List<Product> productList = new ArrayList<>();
                long order_id = resultSet.getLong(1);
                while (resultSet.next()) {
                    if (order_id == resultSet.getLong(1)) {
                        productList.add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                        orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                                , resultSet.getString(3), resultSet.getString(4)), productList,
                                resultSet.getDate(5)));
                    }
                    else break;
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public void createOrder(User user, List<Product> products, LocalDateTime createOrder) {

    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
