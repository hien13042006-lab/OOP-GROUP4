package org.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends MoveableObject{

    private double speed;

    public Paddle(double x, double y, double width, double height, double dx, double dy, double speed) {
        super(x, y, width, height, dx, dy);
        this.speed = speed;
    }

    void moveLeft(double dt) {

    }

    void moveRight(double dt) {

    }

    void PowerUp() {

    }

    @Override
    public void move(double dt) {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        r.draw(this);
    }
}
