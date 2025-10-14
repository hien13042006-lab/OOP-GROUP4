package org.example.demo.Objects;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.demo.GameManager;
import org.example.demo.Renderer;

public class Paddle extends MoveableObject {
    //cac thong so toan cuc cua paddle
    public static final double PADDLE_WIDTH = 100;
    public static final double PADDLE_HEIGHT = 20;
    public static final double PADDLE_SPEED = 800;
    public static final double MARGIN_BOTTOM = 50;

    private double speed;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private EventHandler<KeyEvent> keyPressHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.D) {
                movingRight = true;
                System.out.println("Press D");
            }
            if (e.getCode() == KeyCode.A) {
                movingLeft = true;
                System.out.println("Press A");
            }

        }
    };

    private EventHandler<KeyEvent> keyReleaseHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.D) {
                movingRight = false;
                System.out.println("Release D");
            }
            if (e.getCode() == KeyCode.A) {
                movingLeft = false;
                System.out.println("Release A");
            }

        }
    };

    public EventHandler<KeyEvent> getKeyPressHandler() {
        return keyPressHandler;
    }

    public EventHandler<KeyEvent> getKeyReleaseHandler() {
        return keyReleaseHandler;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Paddle(double x, double y, double width, double height, double dx, double dy, double speed) {
        super(x, y, width, height, dx, dy);
        this.speed = speed;
        image = new Image(getClass().getResourceAsStream("/asset/paddle.png"));
    }

    void moveLeft(double dt) {
        this.x -= this.speed * dx * dt;
    }

    void moveRight(double dt) {
        this.x += this.speed * dx * dt;
    }


    @Override
    public void move(double dt) {
        if (movingLeft) {
            moveLeft(dt);
        }
        if (movingRight) {
            moveRight(dt);
        }

        // Clamp to screen
        if (x < 0) x = 0;
        if (x + width > GameManager.WINDOW_WIDTH) x = GameManager.WINDOW_WIDTH - width;
    }

    @Override
    public void update(double dt) {
        move(dt);
    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }
}
