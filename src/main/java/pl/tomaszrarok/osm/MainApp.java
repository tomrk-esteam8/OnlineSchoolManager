package pl.tomaszrarok.osm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
                initializeDatabase();
            }
        });
    }

    private static void initializeDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Test test = em.find(Test.class, 1);
        if (test == null) {
            test = new Test();
            test.id = 1;
            test.data = "a";

            tx.begin();
            em.persist(test);
            tx.commit();
        }

        System.out.format("Test{id=%s, data=%s}\n", test.id, test.data);

        em.close();
        emf.close();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Online School Manager");
        frame.setContentPane(new MainApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
