package org.example.demo;

public class Brick extends GameObject {
    protected int hitPoints;

    public Brick(){}

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    protected void takeHit() {
        hitPoints--;
    }

    public boolean isDestroyed() {
        if (hitPoints <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {

    }
}
