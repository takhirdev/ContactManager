package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    public static Connection getConnect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/takhirData", "takhir", "takhir7800");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection createTable() {
        Connection connection = null;

        try {
            connection = getConnect();
            String sql = "create table if not exists contact(" +
                    "id serial primary key, " +
                    "name varchar(25), " +
                    "surname varchar(25), " +
                    "phone varchar(12) unique );";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
