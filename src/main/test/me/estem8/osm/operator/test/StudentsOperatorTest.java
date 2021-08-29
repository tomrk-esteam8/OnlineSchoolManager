package me.estem8.osm.operator.test;

import me.esteam8.osm.fields.StudentsFieldCollection;
import me.esteam8.osm.model.Student;
import me.esteam8.osm.operator.StudentsOperator;
import me.esteam8.osm.repository.StudentDAO;
import me.esteam8.osm.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.swing.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class StudentsOperatorTest {

    public static final long STUDENT_1_ID = 1;
    public static final long STUDENT_2_ID = 2;
    public static final long STUDENT_3_ID = 3;
    public static final String UPDATED_FIRSTNAME = "Michael";
    public static final String UPDATED_LASTNAME = "Harris";
    public static final String UPDATED_BANK_ACCOUNT = "000002";
    public static final String UPDATED_EMAIL = "michgael@space.com";
    public static final String UPDATED_PHONE = "+49 123 456 789";
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
    private Map<Long, Student> results;

    @BeforeEach
    public void setup(){
        results = new TreeMap<>();
        Student student1 = new Student("John", "Smith", "000001", "john@space.com", "+48 123 456 789");
        student1.setId(STUDENT_1_ID);
        results.put(STUDENT_1_ID, student1);
        Student student2 = new Student("Michael", "Kowalski", "000002", "kowalski@space.com", "+49 123 456 789");
        student2.setId(STUDENT_2_ID);
        results.put(STUDENT_2_ID, student2);

        when(studentDAO.findAll()).thenReturn(new ArrayList<Student>(results.values()));

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
        assertEquals(results.size(), studentTable.getRowCount());
    }

    @Test
    void saveButtonCreateClickAction() {
        studentTable.clearSelection();

        when(studentDAO.save(any(Student.class))).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        Student student = (Student) invocation.getArguments()[0];
                        student.setId(STUDENT_3_ID);
                        results.put(STUDENT_3_ID, student);

                        return student;
                    }});


        studentFirstnameTextField.setText("Andrew");
        studentLastnameTextField.setText("Scott");
        studentBankAccountTextField.setText("000003");
        studentEmailTextField.setText("andrew@space.com");


        new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository) {
            public void create() {
                init();
                saveButtonClickAction();
            }
        }.create();

        assertEquals(3, results.size());
    }

    @Test
    void saveButtonUpdateClickAction() {

        when(studentDAO.save(any(Student.class))).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        Student student = (Student) invocation.getArguments()[0];
                        results.put(student.getId(), student);

                        return student;
                    }});

        studentFirstnameTextField.setText(UPDATED_FIRSTNAME);
        studentLastnameTextField.setText(UPDATED_LASTNAME);
        studentBankAccountTextField.setText(UPDATED_BANK_ACCOUNT);
        studentEmailTextField.setText(UPDATED_EMAIL);
        studentPhoneTextField.setText(UPDATED_PHONE);

        new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository) {
            public void update() {
                init();
                //select second user
                studentTable.setRowSelectionInterval(1,1);
                saveButtonClickAction();
            }
        }.update();

        assertEquals(UPDATED_FIRSTNAME, results.get(STUDENT_2_ID).getFirstname());
        assertEquals(UPDATED_LASTNAME, results.get(STUDENT_2_ID).getLastname());
        assertEquals(UPDATED_BANK_ACCOUNT, results.get(STUDENT_2_ID).getBankAccount());
        assertEquals(UPDATED_EMAIL, results.get(STUDENT_2_ID).getEmail());
        assertEquals(UPDATED_PHONE, results.get(STUDENT_2_ID).getPhoneNumber());

        assertEquals(2, results.size());
    }

    @Test
    void deleteButtonClickAction() {
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            results.remove(arg0);
            return null;
        }).when(studentDAO).delete(anyLong());

        studentFirstnameTextField.setText(results.get(STUDENT_1_ID).getFirstname());
        studentLastnameTextField.setText(results.get(STUDENT_1_ID).getLastname());
        studentBankAccountTextField.setText(results.get(STUDENT_1_ID).getBankAccount());
        studentEmailTextField.setText(results.get(STUDENT_1_ID).getEmail());
        studentPhoneTextField.setText(results.get(STUDENT_1_ID).getPhoneNumber());

        new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository) {
            public void delete() {
                init();
                //select first user
                studentTable.setRowSelectionInterval(0,0);
                deleteButtonClickAction();
            }
        }.delete();


        assertTrue(studentPhoneTextField.getText().isEmpty());
        assertTrue(studentLastnameTextField.getText().isEmpty());
        assertTrue(studentBankAccountTextField.getText().isEmpty());
        assertTrue(studentEmailTextField.getText().isEmpty());
        assertTrue(studentPhoneTextField.getText().isEmpty());
        assertTrue(studentTable.getSelectionModel().isSelectionEmpty());

        assertEquals(1, results.size());
    }

    @Test
    void clearButtonClickAction() {
        studentFirstnameTextField.setText(results.get(STUDENT_1_ID).getFirstname());
        studentLastnameTextField.setText(results.get(STUDENT_1_ID).getLastname());
        studentBankAccountTextField.setText(results.get(STUDENT_1_ID).getBankAccount());
        studentEmailTextField.setText(results.get(STUDENT_1_ID).getEmail());
        studentPhoneTextField.setText(results.get(STUDENT_1_ID).getPhoneNumber());

        new StudentsOperator(studentTable, studentSaveButton, studentDeleteButton, studentNewButton, studentsFields, repository) {
            public void clear() {
                init();
                //select first user
                studentTable.setRowSelectionInterval(0,0);
                clearButtonClickAction();
            }
        }.clear();

        assertTrue(studentPhoneTextField.getText().isEmpty());
        assertTrue(studentLastnameTextField.getText().isEmpty());
        assertTrue(studentBankAccountTextField.getText().isEmpty());
        assertTrue(studentEmailTextField.getText().isEmpty());
        assertTrue(studentPhoneTextField.getText().isEmpty());
        assertTrue(studentTable.getSelectionModel().isSelectionEmpty());

        assertEquals(2, results.size());
    }

}