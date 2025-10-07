package org.example.demo;

public class Brick extends GameObject {
    public static final int brickWidth = GameManager.WINDOW_WIDTH / 8;
    public static final int brickHeight = 30;

    protected int hitPoints;
    protected double powerUpDropChance;

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

    public PowerUp makePowerUp() {
        return null;
    }

    public double getPowerUpDropChance() {
        return powerUpDropChance;
    }
}
