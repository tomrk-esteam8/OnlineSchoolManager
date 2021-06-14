package pl.tomaszrarok.osm.operator;

import pl.tomaszrarok.osm.fields.StudentsFieldCollection;
import pl.tomaszrarok.osm.fields.TeachersFieldCollection;
import pl.tomaszrarok.osm.repository.StudentsRepository;
import pl.tomaszrarok.osm.repository.TeachersRepository;
import pl.tomaszrarok.osm.table.StudentsTableModel;
import pl.tomaszrarok.osm.table.TeachersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeachersOperator {
    private JTable table;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;
    private TeachersFieldCollection fields;
    private TeachersRepository repository;

    private TeachersTableModel model;

    public TeachersOperator(JTable table, JButton saveButton, JButton deleteButton, JButton newButton, TeachersFieldCollection fields, TeachersRepository repository) {
        this.table = table;
        this.saveButton = saveButton;
        this.deleteButton = deleteButton;
        this.newButton = newButton;
        this.fields = fields;
        this.repository = repository;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fields.getFirstname().setText(repository.getElementAt(table.getSelectedRow()).getFirstname());
                fields.getLastname().setText(repository.getElementAt(table.getSelectedRow()).getLastname());
                fields.getEmail().setText(repository.getElementAt(table.getSelectedRow()).getEmail());
                saveButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fields.getFirstname().setText("");
                fields.getLastname().setText("");
                fields.getEmail().setText("");
                deleteButton.setEnabled(false);
                table.clearSelection();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    fields.getFirstname().setText("");
                    fields.getLastname().setText("");
                    fields.getEmail().setText("");
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
                    repository.saveElementAt(fields.getFirstname().getText(), fields.getLastname().getText(), fields.getEmail().getText(),
                            table.getSelectedRow());
                    model.fireTableRowsUpdated(table.getSelectedRow(), table.getSelectedRow());
                } else {
                    //TODO implement creat, generate id
                }
            }
        });
    }


    public void init(){
        model = new TeachersTableModel(repository);
        table.setModel(model);

        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
}
