module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.graphics;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}