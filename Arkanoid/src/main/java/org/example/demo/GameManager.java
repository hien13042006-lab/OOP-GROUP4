package org.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameManager {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    Renderer r;
    Paddle paddle;
    Canvas canvas;
    Ball ball;
    List<Brick> bricks;
    List<PowerUp> activePowerUps; //các powerUp đang áp dụng
    List<PowerUp> fallingPowerUps; //các powerUp đang rơi

    Random rand = new Random(); // random

    void startGame(Group root) {

        //THONG SO PADDLE
        double paddleWidth = Paddle.PADDLE_WIDTH;
        double paddleHeight = Paddle.PADDLE_HEIGHT;
        double marginBottom = Paddle.MARGIN_BOTTOM; //khoang cach den mep duoi
        double paddleDx = Paddle.PADDLE_DX;
        double paddleDy = Paddle.PADDLE_DY;
        double paddleSpeed = Paddle.PADDLE_SPEED;

        paddle = new Paddle((WINDOW_WIDTH - paddleWidth) / 2,
                WINDOW_HEIGHT - paddleHeight - marginBottom,
                paddleWidth, paddleHeight, paddleDx, paddleDy, paddleSpeed);

        //THONG SO BALL
        int ballRadius = 10;
        int ballDx = 1;
        int ballDy = 1;
        int ballSpeed = 200;

        ball = new Ball(WINDOW_WIDTH / 2 - ballRadius, paddle.y - ballRadius,
                ballRadius * 2, ballRadius * 2,
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

        //powerUp
        activePowerUps = new ArrayList<>();// các powerUp đang rơi
        fallingPowerUps = new ArrayList<>();// các powerUp đang tác dụng

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
        ball.checkCollision(paddle);
        for (int i = 0; i < bricks.size(); i++) {
            if (ball.checkCollision(bricks.get(i))) {
                ball.bounceOffBrick(bricks.get(i));
                bricks.get(i).takeHit();
            }
        }
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).isDestroyed()) {
                //có tỉ lệ rơi ra powerUp
                double chance = rand.nextDouble();
                if(chance < bricks.get(i).getPowerUpDropChance()) {
                    fallingPowerUps.add((PowerUp)bricks.get(i).makePowerUp());
                }
                // xóa khỏi list bricks
                bricks.remove(i);
            }
        }
        //update fallingPowerUps
        for(int i = 0; i < fallingPowerUps.size(); i++) {
            fallingPowerUps.get(i).update(dt);
        }

        //update activePowerUps
        for(int i = 0; i < activePowerUps.size(); i++) {
            if(activePowerUps.get(i).getDuration() <= 0) {
                activePowerUps.get(i).removeEffect(paddle);
                activePowerUps.remove(i);
                i--;
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
        for(PowerUp powerUp : fallingPowerUps) {
            powerUp.render(r);
        }
        for (Brick t : bricks) {
            t.render(r);
        }
    }

}
