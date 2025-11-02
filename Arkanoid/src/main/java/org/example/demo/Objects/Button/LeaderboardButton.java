package org.example.demo.Objects.Button;

import javafx.scene.image.Image;

public class LeaderboardButton extends Button {

    public LeaderboardButton(double x, double y, double width, double height) {
        super(x, y, width, height);
        imageDefault = new Image(getClass().getResourceAsStream("/asset/leaderBoard.png"));
        imageHover = new Image(getClass().getResourceAsStream("/asset/leaderBoardHover.png"));
    }
}
