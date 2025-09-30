package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.PublicKey;

public class Renderer {
    private GraphicsContext gc;

    public Renderer(GraphicsContext gc) {
        this.gc = gc;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    //Xoa man hinh
    public void clear(double width, double height) {
        gc.setFill(Color.BLACK); // nền
        gc.fillRect(0, 0, width, height);
    }

    //ve paddle
    public void draw(Paddle paddle) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    //ve ball
    public void draw(Ball ball) {
        gc.setFill(Color.BLUE);
        gc.fillOval(ball.x, ball.y, ball.width, ball.height);
    }

}
