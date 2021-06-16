package pl.tomaszrarok.osm.operator;

import pl.tomaszrarok.osm.fields.CoursesFieldCollection;
import pl.tomaszrarok.osm.fields.TeachersFieldCollection;
import pl.tomaszrarok.osm.repository.CoursesRepository;
import pl.tomaszrarok.osm.repository.TeachersRepository;
import pl.tomaszrarok.osm.table.CoursesTableModel;
import pl.tomaszrarok.osm.table.TeachersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CoursesOperator {
    private JTable table;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;
    private CoursesFieldCollection fields;
    private CoursesRepository repository;

    private CoursesTableModel model;

    public CoursesOperator(JTable table, JButton saveButton, JButton deleteButton, JButton newButton, CoursesFieldCollection fields, CoursesRepository repository) {
        this.table = table;
        this.saveButton = saveButton;
        this.deleteButton = deleteButton;
        this.newButton = newButton;
        this.fields = fields;
        this.repository = repository;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fields.getName().setText(repository.getElementAt(table.getSelectedRow()).getName());
                saveButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fields.getName().setText("");
                deleteButton.setEnabled(false);
                table.clearSelection();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    fields.getName().setText("");
                    repository.removeElementAt(table.getSelectedRow());
                    model.fireTableRowsDeleted(table.getSelectedRow(), table.getSelectedRow());
                    deleteButton.setEnabled(false);
                    table.clearSelection();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    repository.saveElementAt(fields.getName().getText(),
                            table.getSelectedRow());
                    model.fireTableRowsUpdated(table.getSelectedRow(), table.getSelectedRow());
                } else {
                    //TODO implement creat, generate id
                }
            }
        });
    }


    public void init(){
        model = new CoursesTableModel(repository);
        table.setModel(model);

        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
}
