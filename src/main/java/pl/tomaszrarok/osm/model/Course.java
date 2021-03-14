package pl.tomaszrarok.osm.model;

import lombok.Data;

@Data
public class Course {
    int id;
    String name;
    Double cost;
    Double teacherCost;
    Teacher teacher;
}
