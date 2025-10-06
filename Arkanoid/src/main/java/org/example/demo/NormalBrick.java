package org.example.demo;

import javafx.scene.image.Image;

public class NormalBrick extends Brick {
    double DROP_CHANCE_NORMAL_BRICK = 0.9;
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
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        return new ExpandPaddlePowerUp(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
}
