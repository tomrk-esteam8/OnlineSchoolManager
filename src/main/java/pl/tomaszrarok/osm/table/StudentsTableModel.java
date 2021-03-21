package pl.tomaszrarok.osm.table;

import pl.tomaszrarok.osm.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentsTableModel extends AbstractTableModel {

    private Map<String, Student> data;
    private String[] keys;

    private final String columnNames[] =
            new String[] {
                    "Firstname", "Lastname","E-Mail", "Phone"
            };

    private static final int FIRSTNAME = 0;
    private static final int LASTNAME = 1;
    private static final int EMAIL = 2;
    private static final int PHONE = 3;

    public StudentsTableModel(Map<String, Student> initial) {
        data = initial;
        keys = data.keySet().toArray(new String[data.size()]);
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
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col){
            case FIRSTNAME:
                return data.get(keys[row]).getFirstname();
            case LASTNAME:
                return data.get(keys[row]).getLastname();
            case EMAIL:
                return data.get(keys[row]).getEmail();
            case PHONE:
                return data.get(keys[row]).getPhoneNumbr();
            default:
                return null;
        }
    }
}