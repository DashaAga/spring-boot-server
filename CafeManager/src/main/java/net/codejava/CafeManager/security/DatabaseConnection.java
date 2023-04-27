package net.codejava.CafeManager.security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс подключения к базе данных
 */

public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() {
        // Параметры подключения
        String url = "jdbc:mysql://localhost:3306/data_first";
        String username = "root";
        String password = "root";

        try {
            // Загрузка MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Создание пожключения к базе данных
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    // Закрытие соединения
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
