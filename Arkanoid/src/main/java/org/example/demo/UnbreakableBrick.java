package org.example.demo;

import javafx.scene.image.Image;

public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(double x, double y, double width, double height) {
        this.hitPoints = 1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/unbreakableBrick.png"));
    }

    public UnbreakableBrick() {
        this.hitPoints = 1;
    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public void update(double dt) {
        return;
    }

    @Override
    public void takeHit() {
        return;
    }
}
