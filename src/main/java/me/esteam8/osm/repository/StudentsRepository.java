package me.esteam8.osm.repository;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Student;

@Slf4j
public class StudentsRepository extends BaseRepository<Student, StudentDAO>{

    public StudentsRepository() {
        super(new HashMap<>(), new StudentDAO());
    }


    public void saveElementAt(String firstname, String lastname, String email, String phone, String bankAccount, int index) {
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);
        getElementAt(index).setPhoneNumber(phone);
        getElementAt(index).setBankAccount(bankAccount);

        modelDAO.save(getElementAt(index));
    }

    public Student createElement(String firstname, String lastname, String email, String phone, String bankAccount) {
        Student student = new Student(firstname, lastname, bankAccount, email, phone);

        return modelDAO.save(student);
    }
}
