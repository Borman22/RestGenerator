package poc.rest.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    Connection connection;

    public DataSource(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить коннекшин к БД. Параметры: URL = " + url + ", User = " + user + ", Password = " + password + "\n" + e.getMessage());
        }
    }

    public Connection getConnection() {
        if(connection == null)
            throw new RuntimeException("Соединение с БД было закрыто, поэтому не возможно получить connection");
        return connection;
    }

    public void close() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось закрыть коннекшин с БД. connection = " + connection + "\n" + e.getMessage());
            }
    }
}
