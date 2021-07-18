package me.esteam8.osm.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student extends BaseEntity {

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "bank_account")
    String bankAccount;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;
}
