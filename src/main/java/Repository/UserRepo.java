package Repository;

import config.JDBCConnect;
import config.Properties;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public List<User> read (){
        List<User> users = new ArrayList<>();
        JDBCConnect jdbcConnect = new JDBCConnect(new Properties("C:\\Test\\JSONTest.json"));
        Connection conn =  jdbcConnect.createConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
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
}
