package Main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class LoginPage extends JFrame {
    private JButton ENTERButton;
    private JPasswordField password;
    private JTextField ID_name;
    private JPanel MainPanel;
    private JButton signUpButton;
    private JFrame frame = new JFrame("LoginPage");

    public LoginPage() {
        setSize(500, 500);
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("LoginPage");

        ENTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SQL_SELECT = "SELECT * FROM users WHERE user_id = ? and password_hash = ?";
                //establishes database connection
                //auto closes connection and preparedStatement

                try (java.sql.Connection conn = DriverManager.getConnection("jdbc:postgresql:5678/norange", "norange", "990714");
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
                    preparedStatement.setString(1, ID_name.getText());
                    preparedStatement.setInt(2, Integer.parseInt(password.getText()));
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        frame.setVisible(false);
                        frame.dispose();
                        Main calendar = new Main();
                        calendar.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
                } catch (SQLException c) {
                    System.out.print(c.getMessage());
                } catch (Exception c) {
                    c.printStackTrace();
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                signupPage signup = new signupPage();
                signup.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
//        JFrame parentFrame = new JFrame("Calendar Window");
//        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        parentFrame.setContentPane(new Main());
//        parentFrame.pack();
//        parentFrame.setVisible(true);

        LoginPage loginFrame = new LoginPage();
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        loginFrame.addWindowListener(new WindowAdapter() {
////            @Override
////            public void windowDeactivated(final WindowEvent e) {
////                super.windowDeactivated(e);
////                parentFrame.setVisible(true);
////            }
//        });
        loginFrame.setVisible(true);
    }
}


