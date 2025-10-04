package org.example.demo;

import javafx.scene.image.Image;

public class StrongBrick extends Brick {

    public StrongBrick(double x, double y, double width, double height) {
        this.hitPoints = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/strongBrick.png"));
    }

    StrongBrick() {
        this.hitPoints = 2;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }


}
