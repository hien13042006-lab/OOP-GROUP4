package org.example.demo.Objects.PowerUps;

import javafx.scene.image.Image;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Paddle;

import java.util.List;

//tách ball làm n lan
public class SplitBallPowerUp extends PowerUp {
    public static final int SPLIT_BALL_SPEED = 200;
    public static final double SPLIT_BALL_DURATION = 0.0;
    protected int SPLIT_NUMBER = 3;

    //khởi tạo.
    public SplitBallPowerUp(double x, double y, double width, double height) {
        super();
        speed =  SPLIT_BALL_SPEED;
        duration =  SPLIT_BALL_DURATION;
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

        int n = balls.size();
        for(int i = 0; i < n; i++){
            Ball ball = balls.get(i);
            for(int j = 0; j < SPLIT_NUMBER; j++){
                // 45 do sang trai
                balls.add(new Ball(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight(),
                        -ball.getDy(),ball.getDx(),ball.getSpeed()));

                //45 do sang phai
                balls.add(new Ball(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight(),
                        ball.getDy(),-ball.getDx(),ball.getSpeed()));
            }
        }
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;
    }


}
