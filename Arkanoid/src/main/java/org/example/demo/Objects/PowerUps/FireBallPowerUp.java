package org.example.demo.Objects.PowerUps;

import javafx.scene.image.Image;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Paddle;

import java.util.List;

//bóng lửa đi xuyên và phá hủy các gạch nó đi qua
public class FireBallPowerUp extends PowerUp {
    public static final int FIRE_DAMAGE = 999;
    public static final int FIRE_BALL_SPEED = 200;
    public static final double FIRE_BALL_DURATION = 2.0;

    //khởi tạo.
    public FireBallPowerUp(double x, double y, double width, double height) {
        super();
        speed =  FIRE_BALL_SPEED;
        duration =  FIRE_BALL_DURATION;
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
            ball.setDamage(FIRE_DAMAGE);
            ball.setImage(new Image(getClass().getResourceAsStream("/asset/fireball.png")));
        }
    }

    @Override
    public void removeEffect(Paddle paddle, List<Ball> balls){
        isActive = false;
        for(Ball ball : balls) {
            ball.setDamage(Ball.DAMAGE_DEFAULT);
            ball.setImage(new Image(getClass().getResourceAsStream("/asset/ball.png")));
        }
    }
}
