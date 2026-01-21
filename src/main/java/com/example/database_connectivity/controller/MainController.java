package com.example.database_connectivity.controller;


import com.example.database_connectivity.dao.StudentDAO;
import com.example.database_connectivity.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainController {

    // These match the fx:id in the FXML exactly
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField courseField;

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colEmail;
    @FXML private TableColumn<Student, String> colCourse;

    // Create an instance of your DAO to handle database logic
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    public void initialize() {
        // 1. Link the Table Columns to the Student Class attributes
        // The strings "id", "name", etc. MUST match the getters in Student.java
        // e.g. "name" looks for public String getName()
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        // 2. Load data from database when app starts
        loadData();
    }

    @FXML
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();

        // Basic Validation
        if (name.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Name and Email are required!");
            alert.show();
            return;
        }

        // 3. Send data to Database
        studentDAO.insertStudent(name, email, course);

        // 4. Clear fields and Refresh Table
        nameField.clear();
        emailField.clear();
        courseField.clear();
        loadData();
    }

    private void loadData() {
        // Fetch list from DB and put it into the TableView
        ObservableList<Student> list = FXCollections.observableArrayList(studentDAO.getStudents());
        studentTable.setItems(list);
    }
}