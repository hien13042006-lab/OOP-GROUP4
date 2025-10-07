package org.example.demo;

import java.util.List;

//tăng tốc độ bóng
public class FastBallPowerUp extends PowerUp{
    public static final double FAST_BALL_DURATION = 5.0;
    public static final int FAST_BALL_SPEED = 200;

    //khởi tạo duration.
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
    }

    @Override
    public void applyEffect(Paddle paddle, List<Ball> balls){
        isFalling = false;
        isActive = true;

        for(Ball ball : balls) {
            ball.setSpeed(ball.getSpeed() + FAST_BALL_SPEED);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;

        for(Ball ball : balls) {
            ball.setSpeed(ball.getSpeed() - FAST_BALL_SPEED);
        }
    }
}
