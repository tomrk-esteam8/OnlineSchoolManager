package me.esteam8.osm.table;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Student;
import me.esteam8.osm.repository.StudentsRepository;

import javax.swing.table.AbstractTableModel;

@Slf4j
public class StudentsTableModel extends AbstractTableModel {

    private final StudentsRepository repository;

    private final String columnNames[] =
            new String[] {
                    "ID", "Firstname", "Lastname","E-Mail", "Phone"
            };

    private static final int ID = 0;
    private static final int FIRSTNAME = 1;
    private static final int LASTNAME = 2;
    private static final int EMAIL = 3;
    private static final int PHONE = 4;

    public StudentsTableModel(StudentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (columnIndex < columnNames.length) ? columnNames[columnIndex] : null;
    }

    @Override
    public int getRowCount() {
        return repository.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Student student = repository.getElementAt(row);
        switch(col){
            case ID:
                return student.getId();
            case FIRSTNAME:
                return student.getFirstname();
            case LASTNAME:
                return student.getLastname();
            case EMAIL:
                return student.getEmail();
            case PHONE:
                return student.getPhoneNumber();
            default:
                return null;
        }
    }
}
