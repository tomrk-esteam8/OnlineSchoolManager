package pl.tomaszrarok.osm.model;

import lombok.Data;

@Data
public class Sallary {
    int id;
    Double value;
    Teacher teacher;
}
