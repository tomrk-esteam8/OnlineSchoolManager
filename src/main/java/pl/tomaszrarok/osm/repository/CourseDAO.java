package pl.tomaszrarok.osm.repository;

import pl.tomaszrarok.osm.model.Course;
import pl.tomaszrarok.osm.model.Student;

public class CourseDAO extends BaseDAO<Course> {

    @Override
    protected String getQuery() {
        return "select course from Course course";
    }

}
