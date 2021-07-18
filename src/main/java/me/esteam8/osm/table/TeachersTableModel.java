package me.esteam8.osm.table;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Teacher;
import me.esteam8.osm.repository.TeachersRepository;

import javax.swing.table.AbstractTableModel;

@Slf4j
public class TeachersTableModel extends AbstractTableModel {

    private final TeachersRepository repository;

    private final String columnNames[] =
            new String[] {
                    "ID", "Firstname", "Lastname","E-Mail", "Phone"
            };

    private static final int ID = 0;
    private static final int FIRSTNAME = 1;
    private static final int LASTNAME = 2;
    private static final int EMAIL = 3;
    private static final int PHONE = 4;

    public TeachersTableModel(TeachersRepository repository) {
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
        Teacher teacher = repository.getElementAt(row);
        switch(col){
            case ID:
                return teacher.getId();
            case FIRSTNAME:
                return teacher.getFirstname();
            case LASTNAME:
                return teacher.getLastname();
            case EMAIL:
                return teacher.getEmail();
            case PHONE:
                return teacher.getPhoneNumber();
            default:
                return null;
        }
    }
}
