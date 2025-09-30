package org.example.demo;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameManager {
    Renderer r;
    Paddle paddle;
    Canvas canvas;
    Ball ball;


    void startGame(Group root) {
        paddle = new Paddle(500, 400, 100, 20,0,0,0);
        ball = new Ball(540 ,380,20,20,100,1,1);
        //Paddle ball = new Paddle();
        canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        r = new Renderer(gc);

    }

    void updateGame(double dt) {
        paddle.update(dt);
        ball.update(dt);
    }

    void render() {
        r.clear(800, 600);
        paddle.render(r);
        ball.render(r);
    }

}
