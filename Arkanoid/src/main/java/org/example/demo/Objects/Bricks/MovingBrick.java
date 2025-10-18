package org.example.demo.Objects.Bricks;

import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.FireBallPowerUp;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Renderer;

import static org.example.demo.GameManager.WINDOW_WIDTH;

public class MovingBrick extends Brick {
    int DROP_CHANCE_MOVING_BRICK = 30;
    double speed = 100;
    double dx = 1;

    public MovingBrick(double x, double y, double width, double height) {
        this.hitPoints = 3;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/movingBrick.png"));
        powerUpDropChance = DROP_CHANCE_MOVING_BRICK;
    }

    public MovingBrick() {
        this.hitPoints = 3;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        this.x += this.dx * this.speed * dt;
        if (this.x < 0) {
            this.x = 0;
            dx = -dx;
        } else if (this.x + this.width > WINDOW_WIDTH) {
            this.x = WINDOW_WIDTH - this.width;
            dx = -dx;
        }

    }

    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        return new FireBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}