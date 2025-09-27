package org.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends MoveableObject{
    private Rectangle rect;

    Paddle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new Rectangle(width, height, Color.DARKBLUE);
        rect.setX(x);
        rect.setY(y);
    }

    public Rectangle getNode() {
        return rect;
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
    public void render() {
        rect.setX(x);
        rect.setY(y);
    }
}
