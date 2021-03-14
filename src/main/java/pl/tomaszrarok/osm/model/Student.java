package pl.tomaszrarok.osm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "identification")
    String uniqueIdentification;

    @Column(name = "bank_account")
    String bankAccount;

    @Column(name = "contact")
    String contact;
}
