package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Создание таблицы User(ов)
        // Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        // Очистка таблицы User(ов)
        // Удаление таблицы
        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        userDao.saveUser("Dima", "Tihomirov", (byte) 20);
//        userDao.saveUser("Gadzi", "Gashimov", (byte) 23);
//        userDao.saveUser("Kirill", "Gorshkov", (byte) 45);
//        userDao.saveUser("Andrey", "Sergeevich", (byte) 45);
//        for (User elem : userDao.getAllUsers()) {
//            System.out.println(elem.toString());
//            System.out.println("___________________________________________");
//        }
//        userDao.cleanUsersTable();
//
        userDao.dropUsersTable();
    }
}
