package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class BackButton extends Button {

    public BackButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/back.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/backHover.png"));
    }
}
