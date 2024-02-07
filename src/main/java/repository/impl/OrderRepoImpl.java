package repository.impl;

import config.JDBCConnect;
import entity.Order;
import entity.Product;
import entity.User;
import repository.OrderRepo;

import java.sql.*;
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
                if (orders.isEmpty()) {
                    List<Product> productList = new ArrayList<>();
                    productList.add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                    orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                            , resultSet.getString(3), resultSet.getString(4)), productList,
                            resultSet.getTimestamp(5)));
                } else {
                    Order o = orders.get(orders.size() - 1);
                    if (o.getId() != resultSet.getLong(1)) {
                        List<Product> productList = new ArrayList<>();
                        productList.add(new Product(resultSet.getLong(6),
                                resultSet.getString(8), resultSet.getBigDecimal(9)));
                        orders.add(new Order(resultSet.getLong(1), new User(resultSet.getLong(2)
                                , resultSet.getString(3), resultSet.getString(4)), productList,
                                resultSet.getTimestamp(5)));
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
    public Order findById(Long id) {
        try {
            Connection connection = jdbcConnect.createConnection();
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
                            productList, resultSet.getTimestamp(5));
                } else {
                    result.getProducts().add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                }

            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createOrder(User user, List<Product> products, LocalDateTime createOrder) {

    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
