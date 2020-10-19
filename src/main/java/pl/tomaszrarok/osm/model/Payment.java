package pl.tomaszrarok.osm.model;

import lombok.Data;

@Data
public class Payment {
    int id;
    Double value;
    Student student;
}
