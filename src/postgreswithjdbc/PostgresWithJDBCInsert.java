package postgreswithjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;


public class PostgresWithJDBCInsert {
	String result  = " ";

	void insertUser(Connection connection, String username, String passwordHash) throws SQLException {
		String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, Integer.parseInt(passwordHash));
			preparedStatement.executeUpdate();
			System.out.print("record inserted successfully");
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	void insertEvent{


	void checkUserid(Connection connection, String username){
		String insertQuery = "SELECT username " + username + " COUNT(*)\n" +
				"FROM users\n" +
				"GROUP BY username\n" +
				"HAVING COUNT(*) > 1;\n";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count = resultSet.getInt("count");
			if(count > 1)
				result = "duplicate username";
			else
				result = "username available";

			System.out.print("record inserted successfully");
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}


		
