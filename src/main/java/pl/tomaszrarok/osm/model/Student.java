package pl.tomaszrarok.osm.model;

import lombok.Data;

@Data
public class Student {
    int id;
    String name;
    String uniqueIdentification;
    String bankAccount;
    String contact;
}
