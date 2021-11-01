package me.esteam8.osm.operator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

import me.esteam8.osm.fields.StudentsFieldCollection;
import me.esteam8.osm.repository.StudentsRepository;
import me.esteam8.osm.table.StudentsTableModel;

/**
 * Class to provide operations on student repository within GUI.
 * TODO: validation, logging operations, sort data in table (then create/delete/update has to be adjusted)
 *
 */
public class StudentsOperator {
    private JTable table;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;
    private StudentsFieldCollection fields;
    private StudentsRepository repository;

    private StudentsTableModel model;

    public StudentsOperator(JTable table, JButton saveButton, JButton deleteButton, JButton newButton, StudentsFieldCollection fields, StudentsRepository repository) {
        this.table = table;
        this.saveButton = saveButton;
        this.deleteButton = deleteButton;
        this.newButton = newButton;
        this.fields = fields;
        this.repository = repository;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableClickAction();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearButtonClickAction();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteButtonClickAction();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveButtonClickAction();
            }
        });
    }

    protected void saveButtonClickAction() {
        if (table.getSelectionModel().isSelectionEmpty()) {
            int lastRow= table.getRowCount();
            repository.createElement(fields.getFirstname().getText(), fields.getLastname().getText(),
                    fields.getEmail().getText(),
                    fields.getPhone().getText(), fields.getBankAccount().getText());

            model.fireTableDataChanged();
            table.setRowSelectionInterval(lastRow, lastRow);
        } else {
            repository.saveElementAt(fields.getFirstname().getText(), fields.getLastname().getText(),
                    fields.getEmail().getText(), fields.getPhone().getText(),
                    fields.getBankAccount().getText(),
                    table.getSelectedRow());
            model.fireTableRowsUpdated(table.getSelectedRow(), table.getSelectedRow());
        }
    }

    protected void deleteButtonClickAction() {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            // TODO it may have Parent and clear options
            fields.getFirstname().setText("");
            fields.getLastname().setText("");
            fields.getEmail().setText("");
            fields.getPhone().setText("");
            fields.getBankAccount().setText("");
            repository.removeElementAt(table.getSelectedRow());
            model.fireTableRowsDeleted(table.getSelectedRow(), table.getSelectedRow());
            deleteButton.setEnabled(false);
            table.clearSelection();
        }
    }

    protected void clearButtonClickAction() {
        // TODO it may have Parent and clear options
        fields.getFirstname().setText("");
        fields.getLastname().setText("");
        fields.getEmail().setText("");
        fields.getPhone().setText("");
        fields.getBankAccount().setText("");

        deleteButton.setEnabled(false);
        saveButton.setEnabled(true);
        table.clearSelection();
    }

    protected void tableClickAction() {
        // TODO it may have Parent and set fieldsY

        fields.getFirstname().setText(repository.getElementAt(table.getSelectedRow()).getFirstname());
        fields.getLastname().setText(repository.getElementAt(table.getSelectedRow()).getLastname());
        fields.getEmail().setText(repository.getElementAt(table.getSelectedRow()).getEmail());
        fields.getPhone().setText(repository.getElementAt(table.getSelectedRow()).getPhoneNumber());
        fields.getBankAccount().setText(repository.getElementAt(table.getSelectedRow()).getBankAccount());
        saveButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }


    public void init(){
        model = new StudentsTableModel(repository);
        table.setModel(model);

        saveButton.setEnabled(true);
        deleteButton.setEnabled(false);
    }
}
