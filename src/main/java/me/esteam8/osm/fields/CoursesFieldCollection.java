package me.esteam8.osm.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;

@Data
@AllArgsConstructor
public class CoursesFieldCollection {
    private JTextField name;
    private JComboBox teacher;
}
