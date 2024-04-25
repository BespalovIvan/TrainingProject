package com.example.trainingProject.repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;


@Repository
public class UserRepoImpl implements UserRepo {


    private final JDBCConnect jdbcConnect;


    public UserRepoImpl(JDBCConnect jdbcConnect) {
        this.jdbcConnect = jdbcConnect;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User result = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime());
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }

    }

    @Override
    public Optional<User> findByName(String name) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User result = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime(), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getBoolean(7));
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public Long createUser(UserDto userDto) {
        try (Connection connection = jdbcConnect.createConnection()) {
            Long userId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (username,email,create_date,password,role) " +
                            "VALUES (?,?,?,?,?)", new String[]{"id"});
            preparedStatement.setString(1, userDto.getName());
            preparedStatement.setString(2, userDto.getEmail());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(userDto.getCreateDate()));
            preparedStatement.setString(4, userDto.getPassword());
            preparedStatement.setString(5, userDto.getRole());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong("id");
            }
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public Long updateUser(UserDto userDto) {
        try (Connection connection = jdbcConnect.createConnection()) {
            Long userId = -1L;
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET username = ?,email = ?, create_date = ?, password = ?,role = ?,activate_status = ? WHERE id = ?;"
                            , new String[]{"id"});
            preparedStatement.setString(1, userDto.getName());
            preparedStatement.setString(2, userDto.getEmail());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(userDto.getCreateDate()));
            preparedStatement.setString(4, userDto.getPassword());
            preparedStatement.setString(5, userDto.getRole());
            preparedStatement.setBoolean(6, userDto.isConfirmed());
            preparedStatement.setLong(7, userDto.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong("id");
            }
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public void generateActivateCode(Long userId, String activate_code) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO account_activation_info (user_id,activate_code,confirmed) VALUES (?,?,?)");
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, activate_code);
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public void activateUser(UserDto userDto) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE account_activation_info SET confirmed = ? WHERE user_id = ?");
            preparedStatement.setBoolean(1, userDto.isConfirmed());
            preparedStatement.setLong(2, userDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }
    }

    @Override
    public Optional<User> findNotActivatedUserByCode(String code) {
        try (Connection connection = jdbcConnect.createConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT u.id,u.username,u.email,u.create_date,u.password,u.role FROM users as u JOIN account_activation_info as a ON (u.id = a.user_id) WHERE activate_code = ? AND confirmed = false");
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User result = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime(), resultSet.getString(5),
                        resultSet.getString(6));
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid request", e);
        }


    }
}
