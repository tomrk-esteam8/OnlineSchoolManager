package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Salary;
import pl.tomaszrarok.osm.model.Student;
import pl.tomaszrarok.osm.model.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TeachersRepository extends BaseRepository<Teacher, TeacherDAO> {

    public TeachersRepository() {
        super(new HashMap<>(), new TeacherDAO());
    }

    public void saveElementAt(String firstname, String lastname, String email, int index) {
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);

        modelDAO.save(getElementAt(index));
    }
}
