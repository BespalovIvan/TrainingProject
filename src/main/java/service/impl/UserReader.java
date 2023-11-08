package service.impl;

import config.JDBCConnect;
import entity.User;
import service.Reader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserReader implements Reader<List<User>> {

    @Override
    public List<User> read() {
        JDBCConnect jdbcConnect = new JDBCConnect();
        Connection conn =  jdbcConnect.getPostgresConnection();
        try {
            Statement statement = conn.createStatement();
            return createUser(statement.executeQuery("SELECT * FROM users"));
        } catch (SQLException e) {
            throw new RuntimeException("");
        }
    }
    private List<User> createUser(ResultSet result){
        List<User> users = new ArrayList<>();
        try {
            while (result.next()){
                users.add(new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("failed create user");
        }
    }
}
