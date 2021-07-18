package me.esteam8.osm.repository;

import me.esteam8.osm.model.Teacher;

public class TeacherDAO extends BaseDAO<Teacher> {

    @Override
    protected String getQuery() {
        return "select teacher from Teacher teacher";
    }

}
