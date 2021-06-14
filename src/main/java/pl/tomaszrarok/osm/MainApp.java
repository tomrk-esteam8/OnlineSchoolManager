package pl.tomaszrarok.osm;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.flywaydb.core.Flyway;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.fields.StudentsFieldCollection;
import pl.tomaszrarok.osm.operator.StudentsOperator;
import pl.tomaszrarok.osm.repository.StudentsRepository;
import pl.tomaszrarok.osm.table.StudentsTableModel;

@Slf4j
public class MainApp {


    private JPanel             panel1;
    private JButton            Students;
    private JButton            Teachers;
    private JButton            Courses;
    private JButton            Terms;
    private JButton            Payments;
    private JPanel             StudentsPanel;
    private JPanel             TeachersPanel;
    private JPanel             CoursesPanel;
    private JPanel             CardPanel;

    private JTextField studentFirstnameTextField;
    private JTextField studentLastnameTextField;
    private JTextField studentEmailTextField;
    private JButton studentNewButton;
    private JButton studentSaveButton;
    private JTable studentTable;
    private JButton studentDeleteButton;
    private final StudentsOperator studentsOperator;



    public MainApp() {
        initializeFlyway();

        StudentsFieldCollection studentsFields = new StudentsFieldCollection(studentFirstnameTextField, studentLastnameTextField, studentEmailTextField);
        studentsOperator = new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, new StudentsRepository());

        /**
         * We perform refresh data load for students here.
         */
        Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                studentsOperator.init();
                CardLayout layout = (CardLayout) CardPanel.getLayout();
                layout.show(CardPanel, "Card1");

            }
        });
        Teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout) CardPanel.getLayout();
                layout.show(CardPanel, "Card2");
                log.info("action performed card 2");
            }
        });
        Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout) CardPanel.getLayout();
                layout.show(CardPanel, "Card3");
                log.info("action performed card 3");
            }
        });

        /**
         * Here we can initialize context of initial panel - students.
         */
        CardPanel.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                studentsOperator.init();
            }
        });

    }

    private static void initializeFlyway() {
        try {
            migrateDb();
        } catch (Exception e) {
            connectToDb();
            migrateDb();
        }

    }

    private static void migrateDb() {
        Flyway.configure()
                .dataSource("jdbc:derby:school", "root", "root")
                .baselineOnMigrate(true)
                .createSchemas(true)
                .load().migrate();

    }

    private static void connectToDb() {
        try {
            DriverManager.getConnection("jdbc:derby:school;create=true");
        } catch (SQLException throwables) {
        }
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
