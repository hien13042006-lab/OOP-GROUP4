package org.example.demo;

public class Ball extends MoveableObject{
    private int speed;
    private double directionX;
    private double directionY;

    public Ball(double x, double y, double width, double height, int speed, double directionX, double directionY) {
        super(x, y, width, height);
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    boolean checkCollision(GameObject gameObject) {
        return false;
    }

    void bounceOffPaddle() {

    }

    void bounceOffBrick() {

    }

    void bounceOffWall() {

    }

    @Override
    public void move(double dt) {
        x += directionX * speed * dt;
        y += directionY * speed * dt;
    }

    @Override
    public void update(double dt) {
        move(dt);
    }

    @Override
    public void render(Renderer r) {
        r.draw(this);
    }
}
