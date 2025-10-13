package org.example.demo.Objects.PowerUps;

import javafx.scene.image.Image;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Paddle;

import java.util.List;

//tăng tốc độ bóng
public class FastBallPowerUp extends PowerUp {
    public static final double FAST_BALL_DURATION = 5.0;
    public static final int FAST_BALL_SPEED = 200;
    protected int SPEED_INCREMENT = 300;

    //khởi tạo .
    public FastBallPowerUp(double x, double y, double width, double height) {
        super();
        duration = FAST_BALL_DURATION;
        speed =  FAST_BALL_SPEED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isFalling = true;
        isActive = false;
        image = new Image(getClass().getResourceAsStream("/asset/nitro.png"));
    }

    @Override
    public void applyEffect(Paddle paddle, List<Ball> balls){
        isFalling = false;
        isActive = true;

        for(Ball ball : balls) {
            ball.setSpeed(Ball.SPEED + SPEED_INCREMENT);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;

        for(Ball ball : balls) {
            ball.setSpeed(Ball.SPEED);
        }
    }
}
