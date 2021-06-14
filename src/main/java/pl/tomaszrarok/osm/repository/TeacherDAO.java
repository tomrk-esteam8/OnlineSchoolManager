package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Teacher;

public class TeacherDAO extends BaseDAO<Teacher> {

    @Override
    protected String getQuery() {
        return "select teacher from Teacher teacher";
    }

}
