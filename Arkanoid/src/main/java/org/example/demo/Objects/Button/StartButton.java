package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class StartButton extends Button {

    public StartButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/StartButtonDefault.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/StartButtonHover.png"));
    }
}
