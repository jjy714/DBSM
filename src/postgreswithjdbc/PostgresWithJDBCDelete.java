package postgreswithjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class PostgresWithJDBCDelete{
	public static void main(String[] args){
		String SQL_DELETE = "Delete from STUDENT where ROLL=6;";
		List <Student> studentList = new ArrayList<>();
		String SQL_SELECT = "Select * from STUDENT";
		//establishes database connection
		//auto closes connection and preparedStatement
		try(Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/norange", "norange", "990714");
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)){
			preparedStatement.executeUpdate();
			System.out.println("record deleted successfully");
			//fetch updated record
			PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_SELECT);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while(resultSet.next()){
				int rollId = resultSet.getInt("ROLL");
				String name = resultSet.getString("NAME");
				String section = resultSet.getString("SECTION");		
				Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");
			       Student student = new Student();
		       	student.setRoll(rollId);
	 		student.setName(name);
			student.setSection(section);
			student.setCreatedDate(createdDate.toLocalDateTime());
			studentList.add(student);
			}
	for(Student student: studentList){
		System.out.println("******************************");
		System.out.println("Roll No: " + student.getRoll());
		System.out.println("Name: " + student.getName());
		System.out.println("Section: " + student.getSection());
		}
		preparedStatement1.close();
		
	} catch (SQLException e){
		System.out.print(e.getMessage());
	} catch (Exception e){
		e.printStackTrace();
	}
	}
	}
	

