package org.example.demo.Objects.Bricks;

import java.security.PublicKey;
import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Objects.PowerUps.ReverseControlPowerUp;
import org.example.demo.Objects.PowerUps.SplitBallPowerUp;
import org.example.demo.Renderer;

import java.util.Random;

public class StrongBrick extends Brick {
    int DROP_CHANCE_STRONG_BRICK = 50;
    int SPLIT_BALL_DROP_CHANCE = 30;
    int REVERSE_CONTROL_DROP_CHANCE = 20;

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
        if(chance < SPLIT_BALL_DROP_CHANCE) {
            return new SplitBallPowerUp(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        }
        else return new ReverseControlPowerUp(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
}
