package org.example.demo;

abstract class MoveableObject extends GameObject {
    protected double dx;
    protected double dy;
    double speed;

    public abstract void move(double dt);
}
