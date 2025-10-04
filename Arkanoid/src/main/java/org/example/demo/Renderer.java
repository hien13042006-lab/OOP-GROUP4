package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.security.PublicKey;

public class Renderer {
    private GraphicsContext gc;
    private Image background;

    public Renderer(GraphicsContext gc) {
        this.gc = gc;
        background = new Image(getClass().getResourceAsStream("/asset/background.png"));
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    //Xoa man hinh
    public void clear(double width, double height) {
        gc.drawImage(background, 0, 0, width, height);
    }

    //draw gameObject
    public void draw(GameObject object, Image image) {
        gc.drawImage(image, object.x, object.y, object.width, object.height);
    }

}
