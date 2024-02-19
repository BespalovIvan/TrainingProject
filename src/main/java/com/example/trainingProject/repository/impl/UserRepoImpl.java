package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo {


    private final JDBCConnect jdbcConnect;

    public UserRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }


    @Override
    public List<User> findBetween(Long with, Long by) {
        List<User> users = new ArrayList<>();
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE id BETWEEN ? AND ?");
            preparedStatement.setLong(1, with);
            preparedStatement.setLong(2, by);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User result = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                return Optional.of(result);
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }

    }

    @Override
    public Long createUser(String name, String email) {
        try (Connection connection = jdbcConnect.createConnection()) {
            Long userId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (name,email ) VALUES (?, ?)", new String[]{"id"});
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong("id");
            }
            System.out.println(userId);
            return userId;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public Long updateUserById(Long id, String name, String email) {
        try (Connection connection = jdbcConnect.createConnection()) {
            Long userId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET name = ?, email = ? WHERE id = ?", new String[]{"id"});
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            ResultSet generationKeys = preparedStatement.getGeneratedKeys();
            if (generationKeys.next()) {
                userId = generationKeys.getLong("id");
            }
            System.out.println(userId);
            return userId;
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("invalid request");
        }
    }
}
