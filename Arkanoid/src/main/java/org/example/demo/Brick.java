package org.example.demo;

public class Brick extends GameObject {
    protected int hitPoints;

    protected void takeHit() {
        hitPoints--;
    }

    public boolean isDestroyed() {
        return hitPoints<=0;
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void render(Renderer r) {
    }
}
