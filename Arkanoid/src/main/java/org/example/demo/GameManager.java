package org.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;


public class GameManager {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    Renderer r;
    Paddle paddle;
    Canvas canvas;
    Ball ball;
    List<Brick> bricks;


    void startGame(Group root) {

        //THONG SO PADDLE
        int paddleWidth = 100;
        int paddleHeight = 20;
        int marginBottom = 30; //khoang cach den mep duoi
        int paddleDx = 0;
        int paddleDy = 0;
        int paddleSpeed = 800;
        paddle = new Paddle((WINDOW_WIDTH - paddleWidth) / 2,
                WINDOW_HEIGHT - paddleHeight - marginBottom,
                paddleWidth, paddleHeight, paddleDx, paddleDy, paddleSpeed);

        //THONG SO BALL
        int ballHeight = 13;
        int ballWidth = 12;
        int ballDx = 1;
        int ballDy = 1;
        int ballSpeed = 100;

        ball = new Ball(WINDOW_WIDTH / 2 - ballWidth, paddle.y - ballHeight,
                ballWidth, ballHeight,
                ballDx, ballDy, ballSpeed);


        //ve gach
        bricks = new ArrayList<>();
        int rows = 5;
        int cols = 8; //so gach moi hang
        int brickWidth = WINDOW_WIDTH / cols;
        int brickHeight = 30;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * brickWidth;
                int y = row * brickHeight;

                if ((row + col) % 2 == 0) {
                    bricks.add(new NormalBrick(x, y, brickWidth, brickHeight));
                } else {
                    bricks.add(new StrongBrick(x, y, brickWidth, brickHeight));
                }
            }
        }
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(paddle.getKeyPressHandler());
        canvas.setOnKeyReleased(paddle.getKeyReleaseHandler());
        root.getChildren().add(canvas);
        canvas.requestFocus();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        r = new Renderer(gc);
    }


    void updateGame(double dt) {
        for (int i = 0; i < bricks.size(); i++) {
            if (ball.checkCollision(bricks.get(i))) {
                ball.bounceOffBrick(bricks.get(i));
                bricks.get(i).takeHit();

                if (bricks.get(i).isDestroyed()) {
                    bricks.remove(i);
                    i--;
                }
            }
        }

        paddle.update(dt);
        ball.update(dt);
        if (ball.checkCollision(paddle)) {
            ball.bounceOffPaddle(paddle);
        }
    }

    void render() {
        r.clear(WINDOW_WIDTH, WINDOW_HEIGHT);
        paddle.render(r);
        ball.render(r);
        for (Brick t : bricks) {
            t.render(r);
        }
    }
}
