package me.estem8.osm.operator.test;

import me.esteam8.osm.fields.StudentsFieldCollection;
import me.esteam8.osm.model.Student;
import me.esteam8.osm.operator.StudentsOperator;
import me.esteam8.osm.repository.StudentDAO;
import me.esteam8.osm.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.swing.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentsOperatorTest {

    @Mock
    private StudentDAO studentDAO = mock(StudentDAO.class);;

    private JTextField studentFirstnameTextField;
    private JTextField studentLastnameTextField;
    private JTextField studentEmailTextField;
    private JTextField studentPhoneTextField;
    private JTextField studentBankAccountTextField;
    private JButton studentNewButton;
    private JButton studentSaveButton;
    private JTable studentTable;
    private JButton studentDeleteButton;
    private StudentsFieldCollection studentsFields;
    private StudentsRepository repository;
    private List<Student> list;

    @BeforeEach
    public void setup(){
        list = new ArrayList<>();
        Student student1 = new Student("John", "Smith", "000001", "john@space.com", "+48 123 456 789");
        student1.setId(1);
        list.add(student1);
        Student student2 = new Student("Michael", "Kowalski", "000002", "kowalski@space.com", "+49 123 456 789");
        student2.setId(2);
        list.add(student2);

        when(studentDAO.findAll()).thenReturn(list);

        studentFirstnameTextField = new JTextField();
        studentLastnameTextField = new JTextField();
        studentEmailTextField = new JTextField();
        studentPhoneTextField = new JTextField();
        studentBankAccountTextField = new JTextField();
        studentNewButton = new JButton();
        studentSaveButton= new JButton();
        studentDeleteButton= new JButton();
        studentTable = new JTable();
        repository = new StudentsRepository(studentDAO);
        studentsFields = new StudentsFieldCollection(studentFirstnameTextField, studentLastnameTextField, studentEmailTextField, studentPhoneTextField, studentBankAccountTextField);
    }

    @Test
    void init(){
        StudentsOperator studentsOperator = new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository);

        studentsOperator.init();
        assertEquals(list.size(), studentTable.getRowCount());
    }

    @Test
    void saveButtonCreateClickAction() {
        studentTable.clearSelection();

        when(studentDAO.save(any(Student.class))).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        Student student = (Student) invocation.getArguments()[0];
                        student.setId(3);
                        list.add(student);

                        return student;
                    }});


        studentFirstnameTextField.setText("Andrew");
        studentLastnameTextField.setText("Cambridge");
        studentBankAccountTextField.setText("000003");
        studentEmailTextField.setText("andrew@space.com");


        new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository){
            public void create(){
                init();
                saveButtonClickAction();
            }
        }.create();

        assertEquals(3, list.size());
    }

    @Test
    void saveButtonUpdateClickAction() {
        studentTable.setRowSelectionInterval(1,1);

        studentFirstnameTextField.setText("Michael");
        studentLastnameTextField.setText("Smith");
        studentBankAccountTextField.setText("000002");
        studentEmailTextField.setText("michgael@space.com");
        studentPhoneTextField.setText("+49 123 456 789");
    }

    @Test
    void deleteButtonClickAction() {

    }

    @Test
    void newButtonClickAction() {

    }

    @Test
    void tableClickAction() {

    }
}