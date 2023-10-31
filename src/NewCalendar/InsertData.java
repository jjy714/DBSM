package NewCalendar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/norange";
    private static final String DB_USER = "jason";
    private static final String DB_PASSWORD = "990714";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Insert data into the 'events' schema
            insertEventData(connection, "Birthday Party", "2023-10-31");

            // Insert data into the 'users' schema
            insertUserData(connection, "john_doe", "hashed_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEventData(Connection connection, String eventName, String eventDate) throws SQLException {
        String insertQuery = "INSERT INTO auth_schema.user_events (event_name, event_date) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, eventDate);
            preparedStatement.executeUpdate();
        }
    }

    private static void insertUserData(Connection connection, String username, String passwordHash) throws SQLException {
        String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, Integer.parseInt(passwordHash));
            preparedStatement.executeUpdate();
        }
    }
}

