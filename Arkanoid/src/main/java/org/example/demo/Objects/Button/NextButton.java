package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class NextButton extends Button{
    public NextButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/next.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/nextHover.png"));
    }
}
