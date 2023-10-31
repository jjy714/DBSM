package NewCalendar;

import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
class login extends JFrame {
    //initialize button, panel, label, and text field
    JFrame frame;
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;

    final JTextField textField1, textField2;

    //calling constructor
    login() {

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);
        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
            //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String SQL_SELECT = "SELECT * FROM calendardb.user_info WHERE userid = ? and password = ?";
                //establishes database connection
                //auto closes connection and preparedStatement
                String userValue = textField1.getText();        //get user entered username from the textField1
                String passValue = textField2.getText();

                try (java.sql.Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/norange", "jason", "990714");
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
                    preparedStatement.setString(1, userValue);
                    preparedStatement.setString(2, passValue);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        frame.setVisible(false);
                        frame.dispose();
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
    }
    //define abstract method actionPerformed() which will be called on button click
}

//create the main class
class LoginFormDemo
{
    //main() method start
    public static void main(String arg[])
    {
        try
        {
            //create instance of the CreateLoginForm
            login form = new login();
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            form.setLocation(700,400);
            form.setSize(500,500);  //set size of the frame
            form.setVisible(true);  //make form visible to the user
        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}