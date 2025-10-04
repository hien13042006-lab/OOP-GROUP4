package org.example.demo;

import javafx.scene.image.Image;

public class NormalBrick extends Brick {

    public NormalBrick(double x, double y, double width, double height) {
        this.hitPoints = 1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/normalBrick.png"));
    }

    public NormalBrick() {
        this.hitPoints = 1;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }
}
