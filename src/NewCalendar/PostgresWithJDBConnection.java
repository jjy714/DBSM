package NewCalendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresWithJDBConnection{
	public static void main(String[] args){
		//establishes database connection
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/norange", "jason", "990714")){
			if (connection != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
