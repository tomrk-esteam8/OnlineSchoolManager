package me.esteam8.osm.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;

@Data
@AllArgsConstructor
public class StudentsFieldCollection {
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
}
