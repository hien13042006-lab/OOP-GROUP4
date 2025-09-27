package org.example.demo;

public class Brick extends GameObject {
    protected int hitPoints;

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
    public void render() {

    }
}
