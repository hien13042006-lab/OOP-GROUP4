package org.example.demo;

public class Ball extends MoveableObject{
    private int speed;

    public Ball(double x, double y, double width, double height, double dx, double dy, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        this.dx = dx;
        this.dy = dy;
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
        x += dx * speed * dt;
        y += dy * speed * dt;
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
