package me.esteam8.osm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends BaseEntity {

    String firstname;
    String lastname;
    @Column(name="phone_number")
    String phoneNumber;
    String email;
}
