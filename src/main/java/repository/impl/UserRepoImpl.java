package repository.impl;

import repository.UserRepo;
import config.JDBCConnect;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo{

    private final JDBCConnect jdbcConnect;

    public UserRepoImpl(JDBCConnect jdbcConnect) {
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
            throw new RuntimeException("invalid request");
        }
    }
    @Override
    public User findById(Integer id) {
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

    @Override
    public void createUser(String name, String lastName, String email) {
       try (Connection connection = jdbcConnect.createConnection()) {
           PreparedStatement preparedStatement = connection
                   .prepareStatement("INSERT INTO users (first_name,last_name,email ) VALUES (?, ?, ?)");
           preparedStatement.setString(1,name);
           preparedStatement.setString(2,lastName);
           preparedStatement.setString(3,email);
           preparedStatement.executeUpdate();
       }
       catch (SQLException e){
        throw new RuntimeException("invalid request");
       }
    }

    @Override
    public void updateUserById(Integer id, String name, String lastName, String email) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,email);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        try(Connection connection = jdbcConnect.createConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("invalid request");
        }
    }

}
