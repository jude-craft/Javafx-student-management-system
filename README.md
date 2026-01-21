# Student Database Management System

## Overview

The **Student Database Management System** is a JavaFX-based desktop application that provides a user-friendly interface for managing student information in a PostgreSQL database. It implements the Model-View-Controller (MVC) architecture pattern and demonstrates core database connectivity and CRUD (Create, Read, Update, Delete) operations.

## Project Description

This application allows users to:
- **Add** new student records to the database
- **View** all students in a table format
- **Update** existing student information by selecting a record and modifying the details
- **Delete** student records from the database
- **Clear** input fields and table selections

The system uses prepared statements for secure database queries and provides input validation to ensure data integrity.

## Key Features

- ✅ **CRUD Operations**: Full support for Create, Read, Update, and Delete operations
- ✅ **JavaFX GUI**: Modern, responsive user interface built with JavaFX
- ✅ **PostgreSQL Integration**: Secure connection to PostgreSQL database
- ✅ **Input Validation**: Ensures required fields are filled before operations
- ✅ **Row Selection**: Click a table row to auto-populate edit fields
- ✅ **Real-time Updates**: Table refreshes automatically after database operations
- ✅ **Error Handling**: Alert dialogs for validation and error messages
- ✅ **MVC Architecture**: Clean separation of concerns with Model, View, and Controller layers

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **UI Framework** | JavaFX | 21.0.6 |
| **Build Tool** | Apache Maven | 3.x |
| **Database** | PostgreSQL | 42.7.2 JDBC Driver |
| **Java Version** | Java | 25 |
| **Testing** | JUnit 5 | 5.12.1 |

## Project Structure

```
database_connectivity/
├── pom.xml                                          # Maven configuration with dependencies
├── mvnw                                             # Maven wrapper (Linux/Mac)
├── mvnw.cmd                                         # Maven wrapper (Windows)
├── README.md                                        # This file
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java                     # Java module descriptor
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── database_connectivity/
│   │   │               ├── Main.java                 # Application entry point
│   │   │               ├── model/
│   │   │               │   └── Student.java          # Student data model (POJO)
│   │   │               ├── controller/
│   │   │               │   └── MainController.java   # UI event handlers & business logic
│   │   │               ├── dao/
│   │   │               │   └── StudentDAO.java       # Data Access Object for DB operations
│   │   │               └── util/
│   │   │                   └── DBConnection.java     # Database connection utility
│   │   │
│   │   └── resources/
│   │       └── com/example/database_connectivity/
│   │           └── hello-view.fxml                   # JavaFX UI layout (FXML)
│   │
│   └── test/                                         # Unit tests (can be added)
│
└── target/                                           # Build output directory
    ├── classes/                                      # Compiled classes
    └── generated-sources/                            # Generated source files
```

## Architecture Overview

### Design Pattern: MVC (Model-View-Controller)

```
View (FXML)  ←→  Controller (MainController)  ←→  Model (Student) + DAO
  GUI Layout         Event Handlers              Business Logic & DB Access
```

### Component Responsibilities

#### 1. **Model Layer** (`model/Student.java`)
- Represents the Student entity with properties: `id`, `name`, `email`, `course`
- Contains getters for JavaFX TableView binding
- Plain Old Java Object (POJO) design

#### 2. **View Layer** (`resources/hello-view.fxml`)
- FXML file defining the UI structure
- Components: Text fields for input, TableView for displaying students, buttons for actions
- Linked to MainController for event handling

#### 3. **Controller Layer** (`controller/MainController.java`)
- Handles all user interactions (button clicks, row selections)
- Validates user input before database operations
- Manages UI updates and data refreshing
- Uses StudentDAO for database operations

#### 4. **DAO Layer** (`dao/StudentDAO.java`)
- Data Access Object pattern for database operations
- Methods: `insertStudent()`, `getStudents()`, `updateStudent()`, `deleteStudent()`
- Uses prepared statements for SQL injection prevention

#### 5. **Utility Layer** (`util/DBConnection.java`)
- Manages PostgreSQL database connections
- Centralized configuration (URL, username, password)
- Single point for connection management

#### 6. **Application Entry Point** (`Main.java`)
- Extends `JavaApplication`
- Loads FXML file and initializes the GUI
- Sets up the primary stage (window)

## GUI Overview

### Application Window

The application provides a clean, intuitive interface with the following sections:

```
┌─────────────────────────────────────────────────────┐
│    Student Database Manager                         │
├─────────────────────────────────────────────────────┤
│  [Name Field]  [Email Field]  [Course Field]       │
├─────────────────────────────────────────────────────┤
│  [Add]  [Update]  [Delete]  [Clear]                │
├─────────────────────────────────────────────────────┤
│                                                     │
│  ID  │  Name      │  Email          │  Course     │
│  ────┼────────────┼─────────────────┼─────────    │
│  1   │  John Doe  │  john@email.com │  Java       │
│  2   │  Jane Doe  │  jane@email.com │  Python     │
│  3   │  Bob Smith │  bob@email.com  │  C++        │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### GUI Components

| Component | Purpose | Type |
|-----------|---------|------|
| **Name Field** | Input for student name | TextField |
| **Email Field** | Input for student email | TextField |
| **Course Field** | Input for student course name | TextField |
| **Add Button** | Insert new student (Green) | Button |
| **Update Button** | Modify selected student (Blue) | Button |
| **Delete Button** | Remove selected student (Red) | Button |
| **Clear Button** | Reset all fields | Button |
| **Student Table** | Display all students with columns (ID, Name, Email, Course) | TableView |

### User Interactions

1. **Adding a Student**: Fill in name, email, and course fields, then click "Add"
2. **Selecting a Student**: Click any row in the table to populate the fields with that student's data
3. **Updating a Student**: Select a student, modify the fields, and click "Update"
4. **Deleting a Student**: Select a student and click "Delete"
5. **Clearing Fields**: Click "Clear" to reset all input fields and deselect the table

## Database Schema

### Table: `students`

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100)
);
```

### Prerequisites

- PostgreSQL 10+ installed and running
- Database named `studentdb` created
- Table `students` with schema as shown above

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 25 or higher
- Apache Maven 3.6+
- PostgreSQL 10+
- Git (optional, for cloning the repository)

### Installation Steps

1. **Clone or Download the Project**
   ```bash
   git clone <repository-url>
   cd database_connectivity
   ```

2. **Update Database Credentials**
   - Open `src/main/java/com/example/database_connectivity/util/DBConnection.java`
   - Update the `URL`, `USER`, and `PASS` constants with your PostgreSQL credentials:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/studentdb";
   private static final String USER = "your_username";
   private static final String PASS = "your_password";
   ```

3. **Create the Database and Table**
   ```sql
   CREATE DATABASE studentdb;
   
   CREATE TABLE students (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       course VARCHAR(100)
   );
   ```

4. **Build the Project**
   ```bash
   mvn clean compile
   ```

5. **Run the Application**
   ```bash
   mvn javafx:run
   ```
   
   Or execute the JAR file if packaged:
   ```bash
   java -jar target/database_connectivity-1.0-SNAPSHOT.jar
   ```

## Usage Guide

### Adding a Student
1. Enter the student's name, email, and course in the respective text fields
2. Click the **Add** button
3. The student will be inserted into the database and appear in the table

### Viewing Students
- All students in the database are automatically displayed in the table on startup
- The table shows: ID, Name, Email, and Course columns

### Updating a Student
1. Click on any row in the table to populate the input fields with that student's data
2. Modify the name, email, or course as needed
3. Click the **Update** button to save changes to the database

### Deleting a Student
1. Click on the student row you want to delete
2. Click the **Delete** button
3. The student will be removed from the database and table

### Clearing Fields
- Click the **Clear** button to reset all text fields and deselect the table

## Code Highlights

### SQL Prepared Statements (Security)

The application uses **PreparedStatements** to prevent SQL injection attacks:

```java
String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
try (Connection conn = DBConnection.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, name);
    pstmt.setString(2, email);
    pstmt.setString(3, course);
    pstmt.executeUpdate();
}
```

### Row Selection Listener

When a user clicks a row, the controller automatically populates the fields:

```java
studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        nameField.setText(newSelection.getName());
        emailField.setText(newSelection.getEmail());
        courseField.setText(newSelection.getCourse());
    }
});
```

### Input Validation

```java
private boolean isValidInput() {
    if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
        showAlert("Validation Error", "Name and Email are required!");
        return false;
    }
    return true;
}
```

## Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| javafx-controls | 21.0.6 | JavaFX UI controls (buttons, text fields, etc.) |
| javafx-fxml | 21.0.6 | FXML support for UI layout |
| postgresql | 42.7.2 | PostgreSQL JDBC driver for database connectivity |
| junit-jupiter-api | 5.12.1 | JUnit testing framework |
| controlsfx | 11.2.1 | Extended JavaFX controls |
| bootstrapfx-core | 0.4.0 | Bootstrap styling for JavaFX |

## GUI Screenshots

### Application Screenshots

> **Note**: Add screenshots of the application in action here. Screenshots should include:

#### 1. **Main Application Window**
- Shows the initial state with empty input fields and populated student table
- Demonstrates the FXML layout with all buttons and table visible
- **To Add**: [Insert screenshot path here]

#### 2. **Adding a Student**
- Shows filled input fields (Name, Email, Course)
- After clicking "Add" button
- **To Add**: [Insert screenshot path here]

#### 3. **Updated Table**
- Shows newly added student appearing in the table
- Demonstrates real-time table refresh
- **To Add**: [Insert screenshot path here]

#### 4. **Row Selection and Update**
- Shows a row selected in the table
- Input fields populated with selected student's data
- Ready for update operation
- **To Add**: [Insert screenshot path here]

#### 5. **Validation Alert**
- Shows alert dialog when trying to add without filling required fields
- **To Add**: [Insert screenshot path here]

#### 6. **After Deletion**
- Shows student removed from table after delete operation
- **To Add**: [Insert screenshot path here]

## Troubleshooting

### Issue: "Connection refused" or Database Connection Error
**Solution**: 
- Ensure PostgreSQL is running: `sudo systemctl start postgresql`
- Verify credentials in `DBConnection.java`
- Check that database `studentdb` exists and table `students` is created

### Issue: JavaFX Modules Not Found
**Solution**:
- Ensure Java version is 25 or higher
- Run: `mvn clean install`
- Verify pom.xml has correct JavaFX dependencies

### Issue: FXML File Not Found
**Solution**:
- Ensure `hello-view.fxml` is in `src/main/resources/com/example/database_connectivity/`
- Rebuild the project: `mvn clean compile`

### Issue: Port 5432 Already in Use
**Solution**:
- Check if PostgreSQL is running on a different port
- Update URL in `DBConnection.java` with correct port

## Future Enhancements

- [ ] Search/filter functionality to find students by name or email
- [ ] Export student data to CSV or PDF
- [ ] Add password protection for database access
- [ ] Implement multi-user authentication
- [ ] Add student photo/avatar support
- [ ] Export/Import student records
- [ ] Advanced query filters and sorting options
- [ ] Database backup and restore functionality

## Contributing

Feel free to fork this project and submit pull requests for any improvements or bug fixes.

## License

This project is provided as-is for educational purposes.

## Author

Created as a database connectivity demonstration project using JavaFX and PostgreSQL.

---

**Last Updated**: January 2026

**Project Version**: 1.0-SNAPSHOT
