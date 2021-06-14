package pl.tomaszrarok.osm.repository;

import lombok.extern.slf4j.Slf4j;
import pl.tomaszrarok.osm.model.Course;
import pl.tomaszrarok.osm.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CoursesRepository extends BaseRepository<Course, CourseDAO>{

    public CoursesRepository() {
        super(new HashMap<>(), new CourseDAO());
    }

    public void saveElementAt(int index) {
        modelDAO.save(getElementAt(index));
    }
}
