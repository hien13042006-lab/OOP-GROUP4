package org.example.demo;

import javafx.scene.Group;

public class GameManager {
    Paddle paddle;
    Ball ball;



    void startGame(Group root) {
        paddle = new Paddle(500, 400, 100, 20);
        //Paddle ball = new Paddle();
        root.getChildren().addAll(paddle.getNode());
    }

    void updateGame(double dt) {
        paddle.update(dt);
        ball.update(dt);
    }

    void render() {
        paddle.render();
        ball.render();
    }

}
