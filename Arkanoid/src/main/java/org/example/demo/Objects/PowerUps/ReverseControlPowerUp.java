package org.example.demo.Objects.PowerUps;

import javafx.scene.image.Image;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Paddle;

import java.util.List;

public class ReverseControlPowerUp extends PowerUp {
    public static final int REVERSE_CONTROL_SPEED = 200;
    public static final double REVERSE_CONTROL_DURATION = 2.0;

    //khởi tạo.
    public ReverseControlPowerUp(double x, double y, double width, double height) {
        super();
        speed =  REVERSE_CONTROL_SPEED;
        duration =  REVERSE_CONTROL_DURATION;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isFalling = true;
        isActive = false;
        image = new Image(getClass().getResourceAsStream("/asset/ReverseControlPowerUp.png"));
    }

    @Override
    public void applyEffect(Paddle paddle, List<Ball> balls){

        super.applyEffect(paddle, balls);
        isFalling = false;
        isActive = true;

        paddle.setDx(-1);
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;

        paddle.setDx(1);
    }

}
