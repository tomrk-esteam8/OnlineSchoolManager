package me.esteam8.osm.repository;

import me.esteam8.osm.model.Student;

public class StudentDAO extends BaseDAO<Student> {

    @Override
    protected String getQuery() {
        return "select student from Student student order by id";
    }

}
