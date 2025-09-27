package org.example.demo;

abstract class GameObject {
    double x;
    double y;
    double width;
    double height;


    public abstract void update(double dt);
    public abstract void render();

}
