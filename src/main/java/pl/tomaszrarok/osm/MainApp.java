package pl.tomaszrarok.osm;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Student;
import pl.tomaszrarok.osm.repository.StudentsRepository;
import pl.tomaszrarok.osm.table.StudentsTableModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton newButton;
    private JButton saveButton;
    private JTable table1;
    private JButton deleteButton;
    private StudentsRepository studentsRepository;
    private StudentsTableModel studentsModel;

    public MainApp() {

        /**
         * We perform refresh data load for students here.
         */
        Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initializeStudents();
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card1");

            }
        });
        Teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
                layout.show(CardPanel, "Card2");
                log.info("action performed card 2");
            }
        });
        Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout layout = (CardLayout)CardPanel.getLayout();
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
                initializeStudents();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField1.setText(studentsRepository.getElementAt(table1.getSelectedRow()).getFirstname());
                textField2.setText(studentsRepository.getElementAt(table1.getSelectedRow()).getLastname());
                textField3.setText(studentsRepository.getElementAt(table1.getSelectedRow()).getEmail());
                saveButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                deleteButton.setEnabled(false);
                table1.clearSelection();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if( !table1.getSelectionModel().isSelectionEmpty() ){
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    studentsRepository.removeElementAt(table1.getSelectedRow());
                    studentsModel.fireTableRowsDeleted(table1.getSelectedRow(),table1.getSelectedRow());
                    deleteButton.setEnabled(false);
                    table1.clearSelection();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if( !table1.getSelectionModel().isSelectionEmpty() ){
                    studentsRepository.saveElementAt(textField1.getText(), textField2.getText(), textField3.getText(), table1.getSelectedRow());
                    studentsModel.fireTableRowsUpdated(table1.getSelectedRow(),table1.getSelectedRow());
                } else {
                    //TODO implement creat, generate id
                }
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

    private void initializeStudents() {
        studentsRepository = new StudentsRepository();
        studentsModel =new StudentsTableModel(studentsRepository);
        table1.setModel(studentsModel);

        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
