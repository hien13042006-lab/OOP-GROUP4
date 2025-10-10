package org.example.demo;

import javafx.scene.image.Image;

import java.util.Random;

public class StrongBrick extends Brick {
    int DROP_CHANCE_STRONG_BRICK = 90;
    int SPLIT_BALL_DROP_CHANCE = 90;

    public StrongBrick(double x, double y, double width, double height) {
        this.hitPoints = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/strongBrick.png"));
        powerUpDropChance = DROP_CHANCE_STRONG_BRICK;
    }

    StrongBrick() {
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
        else return null;
    }
}
