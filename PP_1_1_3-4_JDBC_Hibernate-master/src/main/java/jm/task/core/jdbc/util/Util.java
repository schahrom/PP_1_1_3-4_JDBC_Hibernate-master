package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static final String NAME_DB = "mydbtest";
    public static final String DB_URL = "jdbc:mysql://localhost/" + NAME_DB;
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection successfully established.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection not established.");
            return null;
        }
    }
}
