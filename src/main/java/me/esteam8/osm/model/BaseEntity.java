package me.esteam8.osm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@MappedSuperclass
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    long id;
}
