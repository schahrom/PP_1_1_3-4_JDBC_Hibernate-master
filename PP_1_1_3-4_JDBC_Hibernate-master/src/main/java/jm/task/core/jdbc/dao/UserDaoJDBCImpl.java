package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() throws SQLException {}

    public void createUsersTable() {
        String createTable = "CREATE TABLE " + Util.NAME_DB +".`user` (\n" +
                "  `id` BIGINT(100) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` INT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTable);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.err.println("Table not created");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS " + Util.NAME_DB + "." + "user";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTable);
            System.out.println("Table deleted successfully");
        } catch (SQLException e) {
            System.err.println("Table not deleted");
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO " + Util.NAME_DB + ".user (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("A user named - " + name + " has been added to the database");
        } catch (SQLException e) {
            System.err.println("User named - " + name + " is not added to the database");
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + Util.NAME_DB + ".user WHERE id = " + id);
            System.out.println("User ID " + id + " deleted");
        } catch (SQLException e) {
            System.out.println("Error deleting user data");
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT name, lastName, age FROM " + Util.NAME_DB + ".user");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                userList.add(new User(name, lastName, age));
            }
            return userList;
        } catch (SQLException e) {
            System.err.println("Error getting user data");
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE " + Util.NAME_DB + ".user");
            System.out.println("Table data deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting data from table");
            throw new RuntimeException(e);
        }


    }
}
