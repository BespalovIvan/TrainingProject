package config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
    public Connection getPostgresConnection(String path){
        try {
            JSONObject ob = (JSONObject) new JSONParser().parse(new FileReader(path));
            return DriverManager.getConnection((String) ob.get("url"),
                    (String) ob.get("login"),
                    (String) ob.get("password"));
        } catch (SQLException e) {
            throw new RuntimeException("failed to create connection");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
