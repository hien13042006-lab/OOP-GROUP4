package org.example.demo;

public class StrongBrick extends Brick {

    public StrongBrick(double x, double y, double width, double height) {
        this.hitPoints = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    StrongBrick() {
        this.hitPoints = 2;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        r.draw(this);
    }
}
