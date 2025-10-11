package org.example.demo.Objects.Bricks;

import javafx.scene.image.Image;
import org.example.demo.Renderer;

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
    public void takeHit(int damage) {
        return;
    }
}
