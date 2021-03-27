package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class StudentsRepository {
    private Map<String, Student> data = new HashMap<>();

    public StudentsRepository(){
        data.put("1", new Student("John","Smith","00001", "john@awesome.com", "+48 123 456 789"));
        data.put("2",new Student("Garry","Geek","00002", "garry@awesome.com", "+48 321 456 789"));
        data.put("3",new Student("Andrzej","Nowak","00003", "andrzej@awesome.com", "+132 123 456 789"));
        data.put("4",new Student("Misio","Uszatek","00004", "misio@awesome.com", "+48 231 456 789"));
        data.put("5",new Student("Juliusz","Ceaser","00005", "juliusz@awesome.com", "+48 312 456 789"));

        for (Map.Entry<String, Student> entry : data.entrySet()){
            entry.getValue().setId(Integer.parseInt(entry.getKey()));
        }
    }

    private String getKeyAt(int index){
        return data.keySet().toArray(new String[data.size()])[index];
    }


    public Student getElementAt(int index){
        return data.get(getKeyAt(index));
    }

    public void saveElementAt(String firstname, String lastname, String email, int index){
        getElementAt(index).setFirstname(firstname);
        getElementAt(index).setLastname(lastname);
        getElementAt(index).setEmail(email);
    }

    public void addElement(String firstname, String lastname, String email){

    }

    public int size() {
        return data.size();
    }

    public void removeElementAt(int index) {
        data.remove(getKeyAt(index));
    }
}
