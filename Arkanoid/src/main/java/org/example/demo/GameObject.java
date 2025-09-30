package org.example.demo;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public GameObject(){}
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(double dt);
    public abstract void render(Renderer r);

}
