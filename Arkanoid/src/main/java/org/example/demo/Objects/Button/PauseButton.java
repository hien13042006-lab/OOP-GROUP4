package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class PauseButton extends Button {

    public PauseButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/pause.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/pause.png"));
    }
}
