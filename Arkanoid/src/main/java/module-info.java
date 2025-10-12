module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.graphics;
    requires java.logging;
    requires java.sql;
    requires eu.hansolo.tilesfx;
    //requires org.example.demo;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.States;
    opens org.example.demo.States to javafx.fxml;
    exports org.example.demo.Objects;
    opens org.example.demo.Objects to javafx.fxml;
    exports org.example.demo.Levels;
    opens org.example.demo.Levels to javafx.fxml;
    exports org.example.demo.Objects.PowerUps;
    opens org.example.demo.Objects.PowerUps to javafx.fxml;
    exports org.example.demo.Objects.Bricks;
    opens org.example.demo.Objects.Bricks to javafx.fxml;
}