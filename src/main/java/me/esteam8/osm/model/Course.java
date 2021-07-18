package me.esteam8.osm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course extends BaseEntity {
    String name;
    Double cost;
    @Column(name = "teacher_cost")
    Double teacherCost;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

}
