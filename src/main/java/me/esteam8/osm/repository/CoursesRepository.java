package me.esteam8.osm.repository;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Course;

import java.util.HashMap;
import java.util.TreeMap;

@Slf4j
public class CoursesRepository extends BaseRepository<Course, CourseDAO>{

    public CoursesRepository() {
        super(new TreeMap<>(), new CourseDAO());
    }

    public void saveElementAt(String name, int index) {
        getElementAt(index).setName(name);

        modelDAO.save(getElementAt(index));
    }
}
