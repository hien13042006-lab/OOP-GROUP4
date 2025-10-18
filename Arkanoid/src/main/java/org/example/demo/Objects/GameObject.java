package org.example.demo.Objects;

import javafx.scene.image.Image;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;


    public GameObject() {
    }

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public abstract void update(double dt);

    public abstract void render(Renderer r);

}
