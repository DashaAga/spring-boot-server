package net.codejava.CafeManager.security;

import net.codejava.CafeManager.security.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс получения коллекции с данными о ролях пользователей из базы данных
 */

public class DatabaseQuery {

    private DatabaseConnection databaseConnection;

    public DatabaseQuery() {
        databaseConnection = new DatabaseConnection();
    }

    public List<List> getData() {
        List<List> users = new ArrayList<>();

        try {
            // Получаем соединение с базой данных
            Connection connection = databaseConnection.getConnection();

            // Создаем statement объект
            Statement statement = connection.createStatement();

            // Выполняем запрос и считываем его результат
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

            // Добавляем каждую запись в коллекцию с данными о ролях пользователей
            while (resultSet.next()) {
                List<String> data = new ArrayList<>();
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                data.add(login);
                data.add(password);
                data.add(role);
                users.add(data);
            }


            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем наше соединение
            databaseConnection.closeConnection();
        }

        return users;
    }
}
