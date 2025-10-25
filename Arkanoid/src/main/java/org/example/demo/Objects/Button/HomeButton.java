package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class HomeButton extends Button {
    public HomeButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/home.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/homeHover.png"));
    }
}
