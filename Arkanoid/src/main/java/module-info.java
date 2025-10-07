module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.graphics;
    requires java.logging;
    requires java.sql;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}