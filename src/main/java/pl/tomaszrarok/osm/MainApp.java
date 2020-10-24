package pl.tomaszrarok.osm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    private JPanel panel1;
    private JButton Students;
    private JButton Teachers;
    private JButton Courses;
    private JButton Terms;
    private JButton Payments;
    private JPanel StudentsPanel;
    private JPanel TeachersPanel;
    private JPanel CoursesPanel;
    private JPanel CardPanel;

    public MainApp() {
        Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card1");
            }
        });
        Teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card2");
            }
        });
        Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card3");
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Online School Manager");
        frame.setContentPane(new MainApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
