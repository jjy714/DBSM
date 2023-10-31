package Main;

import javax.swing.*;
import java.awt.*;
class CalendarProgram{
    public static void main(String args[]){

        //Main Frame
        JFrame frame = new JFrame("DBMS Calendar Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);

        //Menu Bar
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
    }
}