module com.example.database_connectivity {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.database_connectivity to javafx.fxml;

    opens com.example.database_connectivity.controller to javafx.fxml;

    opens com.example.database_connectivity.model to javafx.base;

    exports com.example.database_connectivity;
}