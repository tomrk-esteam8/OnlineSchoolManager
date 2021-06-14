package pl.tomaszrarok.osm.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;

@Data
@AllArgsConstructor
public class TeachersFieldCollection {
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
}
