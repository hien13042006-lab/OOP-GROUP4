package org.example.demo.Objects.Bricks;

import java.util.Random;
import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.ExpandPaddlePowerUp;
import org.example.demo.Objects.PowerUps.FireBallPowerUp;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Objects.PowerUps.ReverseControlPowerUp;
import org.example.demo.Objects.PowerUps.SplitBallPowerUp;
import org.example.demo.Renderer;

public class StrongBrick extends Brick {

    int DROP_CHANCE_STRONG_BRICK = 25;
    int SPLIT_BALL_DROP_CHANCE = 10;
    int REVERSE_CONTROL_DROP_CHANCE = 15;
    int EXPEND_DROP_CHANCE = 20;
    int FIRE_BALL_DROP_CHANCE = 25;

    public StrongBrick(double x, double y, double width, double height) {
        this.hitPoints = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/strongBrick.png"));
        powerUpDropChance = DROP_CHANCE_STRONG_BRICK;
    }

    public StrongBrick() {
        this.hitPoints = 2;
        image = new Image(getClass().getResourceAsStream("/asset/strongBrick.png"));
    }


    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        Random rand = new Random();
        int chance = rand.nextInt(powerUpDropChance);
        if (chance < SPLIT_BALL_DROP_CHANCE) {
            return new SplitBallPowerUp(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
        } else if (chance < REVERSE_CONTROL_DROP_CHANCE) {
            return new ReverseControlPowerUp(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
        } else if (chance < EXPEND_DROP_CHANCE) {
            return new ExpandPaddlePowerUp(this.getX(), this.getY(), this.getWidth(),
                    this.getHeight());
        } else {
            return new FireBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }
}
