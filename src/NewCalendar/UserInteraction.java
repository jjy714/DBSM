package NewCalendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class UserInteraction {
    String userid;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/norange";

    private static final String username = "norange";

    private static final String password = "990714";

    public UserInteraction(String userid){
        this.userid = userid;
    }

    public void GetUserInfo(UserInteraction userid) {
        //get user info from database
        String checkQuery = "SELECT username " + userid + " COUNT(*)\n" +
                "FROM users\n" +
                "GROUP BY username\n" +
                "HAVING COUNT(*) > 1;\n";
        try (Connection connection = DriverManager.getConnection(DB_URL, username, password)){
        PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);

    }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateEvent(UserInteraction userid, int startTime, int endTime, String[] users, String subject, String description) {
        //create event in database

    }
    public void UpdateEvent(int eventID, int startTime, int endTime, String[] users, String subject, String description) {
        //update event in database
    }
    public void SetReminder(UserInteraction userid, Boolean userDefault, String method, Boolean effective, int interval) {


    }

}
