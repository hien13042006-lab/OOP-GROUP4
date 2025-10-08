package org.example.demo;

import javafx.scene.image.Image;

import java.util.List;

public class ExpandPaddlePowerUp extends PowerUp {

    public static final double EXPAND_DURATION = 7.0;
    public static final int EXPAND_SPEED = 200;

    //khởi tạo.
    public ExpandPaddlePowerUp(double x, double y, double width, double height) {
        super();
        duration = EXPAND_DURATION;
        speed =  EXPAND_SPEED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isFalling = true;
        isActive = false;
        image = new Image(getClass().getResourceAsStream("/asset/ball.png"));
    }

    @Override
    public void applyEffect(Paddle paddle, List<Ball> balls){
        isFalling = false;
        isActive = true;
        //độ tăng chiều rộng paddle.
        double paddleIncrement = Paddle.PADDLE_WIDTH;

        //giảm tọa độ theo x đi 1/2 mức tăng.
        paddle.setX(paddle.getX()-paddleIncrement/2);
        //tăng chiều rộng.
        paddle.setWidth(paddle.getWidth()+paddleIncrement);
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;
        //độ giảm chiều rộng paddle.
        double paddleDecrement = Paddle.PADDLE_WIDTH;

        //giảm tọa độ theo x đi 1/2 mức tăng.
        paddle.setX(paddle.getX()+paddleDecrement/2);
        //tăng chiều rộng.
        paddle.setWidth(paddle.getWidth()-paddleDecrement);
    }
}
