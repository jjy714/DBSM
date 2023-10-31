package postgreswithjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signUp{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/calendar_db";
    private static final String DB_USER = "norange";
    private static final String DB_PASSWORD = "990714";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            insertUser(connection, "john_doe", "hashed_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertUser(Connection connection, String username, String passwordHash) throws SQLException {
        String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.executeUpdate();
        }
    }
}
