package poc.rest.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    Connection connection;

    private String url;
    private String user;
    private String password;

    public DataSource(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        if(connection == null)
            try {
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось получить коннекшин к БД. Параметры: URL = " + url + ", User = " + user + ", Password = " + password + "\n" + e.getMessage());
            }
        else
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
