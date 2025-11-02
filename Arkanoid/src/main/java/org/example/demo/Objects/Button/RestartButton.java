package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class RestartButton extends Button {

    public RestartButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/restart.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/restartHover.png"));
    }

}
