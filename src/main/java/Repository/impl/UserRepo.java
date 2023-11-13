package Repository.impl;

import Repository.Repository;
import config.JDBCConnect;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements Repository<User> {

    private final JDBCConnect jdbcConnect;

    public UserRepo(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public List<User> findAll (){
        List<User> users = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("failed read file");
        }
    }

    @Override
    public User find(Integer id) {
        try (Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }else throw new RuntimeException("user not found");
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }
}
