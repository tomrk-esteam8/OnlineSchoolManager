package me.esteam8.osm.repository;

import me.esteam8.osm.model.Course;

public class CourseDAO extends BaseDAO<Course> {

    @Override
    protected String getQuery() {
        return "select course from Course course";
    }

}
