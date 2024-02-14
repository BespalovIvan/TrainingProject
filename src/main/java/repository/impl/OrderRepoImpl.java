package repository.impl;

import config.JDBCConnect;
import entity.Order;
import entity.Product;
import entity.User;
import repository.OrderRepo;

import java.sql.*;
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
                            productList, resultSet.getTimestamp(5).toLocalDateTime());
                } else {
                    result.getProducts().add(new Product(resultSet.getLong(6),
                            resultSet.getString(8), resultSet.getBigDecimal(9)));
                }
            }
            if (result == null) {
                throw new RuntimeException("User not found!");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orders = new ArrayList<>();
        Connection connection = jdbcConnect.createConnection();
        try {
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
            if(orders.isEmpty()){
                throw new RuntimeException("User not found");
            }
            return orders;

        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }

    }

    @Override
    public void createOrder(Long userId, Date dateTime, Long productId, Integer countProducts) {
            Connection connection = jdbcConnect.createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DO $$" +
                    "DECLARE order_id bigint;" +
                    "BEGIN " +
                    "INSERT INTO orders (user_id,datetime) VALUES (?,?) RETURNING id " +
                    "INTO order_id; " +
                    "INSERT INTO order_products (order_id,product_id,product_quantity) " +
                    "VALUES (order_id,?,?); COMMIT;" +
                    "END$$ ");
            preparedStatement.setLong(1,userId);
            preparedStatement.setDate(2,dateTime);
            preparedStatement.setLong(3,productId);
            preparedStatement.setInt(4,countProducts);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Integer id) {

    }
}
