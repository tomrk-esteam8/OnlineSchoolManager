package pl.tomaszrarok.osm.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Student;

@Slf4j
public class StudentsRepository {

    private final StudentDAO           studentDAO = new StudentDAO();
    private final Map<String, Student> data       = new HashMap<>();

    public StudentsRepository() {
        List<Student> students = studentDAO.findAll();
        students.forEach(s -> data.put("" + s.getId(), s));
    }

    public void saveElementAt(String firstname, String lastname, String email, int index) {
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);

        studentDAO.save(getElementAt(index));
    }

    public Student getElementAt(int index) {
        return data.get(getKeyAt(index));
    }

    private String getKeyAt(int index) {
        return data.keySet().toArray(new String[data.size()])[index];
    }

    public void addElement(String firstname, String lastname, String email) {

    }

    public int size() {
        return data.size();
    }

    public void removeElementAt(int index) {
        studentDAO.delete((long) index);
        data.remove(getKeyAt(index));
    }
}
