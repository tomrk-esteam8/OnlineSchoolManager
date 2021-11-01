package me.esteam8.osm.operator;

import me.esteam8.osm.fields.TeachersFieldCollection;
import me.esteam8.osm.repository.TeachersRepository;
import me.esteam8.osm.table.TeachersTableModel;

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
                fields.getPhone().setText(repository.getElementAt(table.getSelectedRow()).getPhoneNumber());
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
                fields.getPhone().setText("");
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
                    fields.getPhone().setText("");
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
                    int lastRow= table.getRowCount();
                    repository.createElement(fields.getFirstname().getText(), fields.getLastname().getText(),
                            fields.getEmail().getText(),
                            fields.getPhone().getText());

                    model.fireTableDataChanged();
                    table.setRowSelectionInterval(lastRow, lastRow);
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
