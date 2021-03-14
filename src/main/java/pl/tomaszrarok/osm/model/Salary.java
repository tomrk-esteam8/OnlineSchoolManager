package pl.tomaszrarok.osm.model;

import lombok.Data;

@Data
public class Salary {
    int id;
    Double value;
    Teacher teacher;
}
