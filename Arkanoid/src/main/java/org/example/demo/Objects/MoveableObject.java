package org.example.demo.Objects;

public abstract class MoveableObject extends GameObject {
    protected double dx;
    protected double dy;

    public MoveableObject() {
    }

    public MoveableObject(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, width, height);
        this.dx = dx;
        this.dy = dy;
    }

    public MoveableObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public abstract void move(double dt);
}
