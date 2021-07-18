package me.esteam8.osm;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

    @Id
    public int id;

    @Basic
    public String data;
}