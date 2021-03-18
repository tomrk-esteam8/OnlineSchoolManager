package pl.tomaszrarok.osm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {

    @Column(name = "id")
    String id;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "bank_account")
    String bankAccount;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumbr;
}
