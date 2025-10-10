package org.example.demo.Objects.Bricks;

import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.ExpandPaddlePowerUp;
import org.example.demo.Objects.PowerUps.FastBallPowerUp;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Renderer;

import java.util.Random;

public class NormalBrick extends Brick {
    int DROP_CHANCE_NORMAL_BRICK = 90;
    int EXPEND_DROP_CHANCE = 50;
    int FAST_BALL_DROP_CHANCE = 40;
    public NormalBrick(double x, double y, double width, double height) {
        this.hitPoints = 1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/normalBrick.png"));
        powerUpDropChance = DROP_CHANCE_NORMAL_BRICK;
    }

    public NormalBrick() {
        this.hitPoints = 1;
        powerUpDropChance = DROP_CHANCE_NORMAL_BRICK;
    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        Random rand = new Random();
        int chance = rand.nextInt(powerUpDropChance);
        if(chance < EXPEND_DROP_CHANCE) {
            return new ExpandPaddlePowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            return new FastBallPowerUp(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        }
    }
}
