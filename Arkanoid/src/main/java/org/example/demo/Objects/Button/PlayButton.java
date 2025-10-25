package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class PlayButton extends Button {

    public PlayButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/play.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/playHover.png"));
    }
}
