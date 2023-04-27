module com.example.c868jbruening {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c868jbruening to javafx.fxml;
    exports com.example.c868jbruening;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    opens model to javafx.base;
}