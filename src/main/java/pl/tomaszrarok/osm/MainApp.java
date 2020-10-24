package pl.tomaszrarok.osm;

import javax.swing.*;

public class MainApp {
    private JPanel panel1;
    private JButton Students;
    private JButton Teachers;
    private JButton Courses;
    private JButton Terms;
    private JButton Payments;

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
