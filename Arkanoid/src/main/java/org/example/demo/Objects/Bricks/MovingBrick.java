package org.example.demo.Objects.Bricks;

import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.*;
import org.example.demo.Renderer;

import java.util.Random;

import static org.example.demo.GameManager.WINDOW_WIDTH;

public class MovingBrick extends Brick {
    int DROP_CHANCE_MOVING_BRICK = 30;
    int EXPEND_DROP_CHANCE = 10;
    int FAST_BALL_DROP_CHANCE = 15;
    int SPLIT_BALL_DROP_CHANCE = 20;
    int REVERSE_CONTROL_DROP_CHANCE = 25;
    int FIRE_BALL_DROP_CHANCE = 30;
    double speed = 100;
    double dx = 1;

    public MovingBrick(double x, double y, double width, double height) {
        this.hitPoints = 3;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/movingBrick.png"));
        powerUpDropChance = DROP_CHANCE_MOVING_BRICK;
    }

    public MovingBrick() {
        this.hitPoints = 3;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        this.x += this.dx * this.speed * dt;
        if (this.x < 0) {
            this.x = 0;
            dx = -dx;
        } else if (this.x + this.width > WINDOW_WIDTH) {
            this.x = WINDOW_WIDTH - this.width;
            dx = -dx;
        }

    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        Random rand = new Random();
        int chance = rand.nextInt(powerUpDropChance);
        if (chance < EXPEND_DROP_CHANCE) {
            return new ExpandPaddlePowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else if(chance < FAST_BALL_DROP_CHANCE) {
            return new FastBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else if(chance < SPLIT_BALL_DROP_CHANCE) {
            return new SplitBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else if(chance < REVERSE_CONTROL_DROP_CHANCE) {
            return new ReverseControlPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            return new FireBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }
}