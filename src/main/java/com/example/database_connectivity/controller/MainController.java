package com.example.database_connectivity.controller;


import com.example.database_connectivity.dao.StudentDAO;
import com.example.database_connectivity.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField courseField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colEmail;
    @FXML private TableColumn<Student, String> colCourse;

    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        loadData();

        // SENIOR TIP: Add a listener to handle row selection
        // When a user clicks a row, populate the text fields
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getName());
                emailField.setText(newSelection.getEmail());
                courseField.setText(newSelection.getCourse());
            }
        });
    }

    @FXML
    protected void onAddButtonClick() {
        if (isValidInput()) {
            studentDAO.insertStudent(nameField.getText(), emailField.getText(), courseField.getText());
            refreshTable();
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Error", "Please select a student to update.");
            return;
        }

        if (isValidInput()) {
            // Create a new student object with the OLD ID but NEW info
            Student updatedStudent = new Student(
                    selected.getId(), // Keep the ID!
                    nameField.getText(),
                    emailField.getText(),
                    courseField.getText()
            );

            studentDAO.updateStudent(updatedStudent);
            refreshTable();
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Error", "Please select a student to delete.");
            return;
        }

        // Confirm deletion (Optional but recommended)
        studentDAO.deleteStudent(selected.getId());
        refreshTable();
    }

    @FXML
    protected void onClearButtonClick() {
        clearFields();
        studentTable.getSelectionModel().clearSelection();
    }

    private void refreshTable() {
        loadData();
        clearFields();
    }

    private void loadData() {
        ObservableList<Student> list = FXCollections.observableArrayList(studentDAO.getStudents());
        studentTable.setItems(list);
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        courseField.clear();
    }

    private boolean isValidInput() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            showAlert("Validation Error", "Name and Email are required!");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}