package me.esteam8.osm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;
    
    LocalDate date;

    //TODO check how it works, in case different teacher then payment would be different, otherwise
    @Column(name="teacher_cost")
    Double teacherCost;
}
