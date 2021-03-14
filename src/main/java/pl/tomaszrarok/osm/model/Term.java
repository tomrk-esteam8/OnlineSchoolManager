package pl.tomaszrarok.osm.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Term {
    int id;
    Course course;
    Teacher teacher;
    Student student;
    LocalDate date;

    //TODO check how it works, in case different teacher then payment would be different, otherwise
    Double costTeacher;
}
