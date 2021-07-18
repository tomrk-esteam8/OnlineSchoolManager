package me.esteam8.osm.table;

import lombok.extern.slf4j.Slf4j;
import me.esteam8.osm.model.Course;
import me.esteam8.osm.repository.CoursesRepository;

import javax.swing.table.AbstractTableModel;

@Slf4j
public class CoursesTableModel extends AbstractTableModel {

    private final CoursesRepository repository;

    private final String columnNames[] =
            new String[] {
                    "ID", "Firstname", "Teacher"
            };

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int TEACHER = 2;

    public CoursesTableModel(CoursesRepository repository) {
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
        Course course = repository.getElementAt(row);
        switch(col){
            case ID:
                return course.getId();
            case NAME:
                return course.getName();
            case TEACHER:
                return String.format("%s %s", course.getTeacher().getFirstname(), course.getTeacher().getLastname());
            default:
                return null;
        }
    }
}
