package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class signupPage extends JFrame{
    private JPanel MainPanel;
    private JTextField UserID;
    private JTextField Password;
    private JButton SignUpConfirm;
    private JButton ConfirmID;


    public signupPage() {


        SignUpConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SQL_INSERT = "INSERT INTO USER (ID, PASSWORD, CREATED_DATE) VALUES (?, ?, ?)";
                //establishes database connection
                //auto closes connection and preparedStatement
                try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/norange", "jason", "990714");
                     PreparedStatement preparedStatement = conn.prepareStatement (SQL_INSERT)){
                    //insert student record
                    preparedStatement.setString(1,UserID.getText()); //1 specifiesa the first parameter in the query
                    preparedStatement.setInt(2, Integer.parseInt(Password.getText()));
                    preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
                    preparedStatement.executeUpdate();
                    System.out.print("record inserted successfully");
                } catch (SQLException c){
                    System.out.print(c.getMessage());
                } catch (Exception c){
                    c.printStackTrace();
                }
            }
        });
        ConfirmID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SQL_SELECT = "SELECT * FROM USER WHERE ID = ?";
                //establishes database connection
                //auto closes connection and preparedStatement

                try(Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5678/norange", "norange", "990714");
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)){
                    preparedStatement.setString(1, UserID.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        JOptionPane.showMessageDialog(null, "ID already exists");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ID is available");
                        JOptionPane.showConfirmDialog(null, "Do you want to use this ID?", "ID Confirmation", JOptionPane.YES_NO_OPTION);
                        if(JOptionPane.YES_OPTION == 0){
                            UserID.setEditable(false);
                        }
                        else{
                            UserID.setText(UserID.getText());
                        }
                    }
                } catch (SQLException c){
                    System.out.print(c.getMessage());
                } catch (Exception c){
                    c.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("signupPage");
        frame.setContentPane(new signupPage().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
