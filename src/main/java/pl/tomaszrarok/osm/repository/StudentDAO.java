package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Student;

public class StudentDAO extends BaseDAO<Student> {

    @Override
    protected String getQuery() {
        return "select student from Student student";
    }

}
