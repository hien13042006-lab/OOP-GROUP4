package org.example.demo.Objects.Bricks;

import java.util.Random;
import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.ExpandPaddlePowerUp;
import org.example.demo.Objects.PowerUps.FastBallPowerUp;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Objects.PowerUps.SplitBallPowerUp;
import org.example.demo.Renderer;

public class NormalBrick extends Brick {

    int DROP_CHANCE_NORMAL_BRICK = 20;
    int EXPEND_DROP_CHANCE = 10;
    int FAST_BALL_DROP_CHANCE = 15;
    int SPLIT_BALL_DROP_CHANCE = 20;

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
        if (chance < EXPEND_DROP_CHANCE) {
            return new ExpandPaddlePowerUp(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
        } else if (chance < FAST_BALL_DROP_CHANCE) {
            return new FastBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            return new SplitBallPowerUp(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
        }
    }
}
