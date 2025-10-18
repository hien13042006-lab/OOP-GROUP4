package org.example.demo.Objects.PowerUps;

import org.example.demo.GameManager;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.GameObject;
import org.example.demo.Objects.Paddle;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

import java.util.List;

public abstract class PowerUp extends GameObject {
    protected double duration;
    protected boolean isFalling;
    protected boolean isActive;
    protected double dx = 0;
    protected double dy = 1;
    protected int speed;

    public PowerUp() {

    }

    public void applyEffect(Paddle paddle, List<Ball> balls) {
        GameManager.soundManager.playSoundEffect("PowerUpApply");
    }


    public void removeEffect(Paddle paddle, List<Ball> balls) {

    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public void update(double dt) {
        if (isFalling) {
            y += this.speed * dt;
        }

        if (isActive) {
            this.setDuration(this.getDuration() - dt);
        }
    }

    @Override
    public void render(Renderer r) {
        r.draw(this, this.getImage());
    }

    // check va chạm với paddle để kích hoạt
    public boolean checkCollision(Paddle paddle) {
        return this.x < paddle.getX() + paddle.getWidth() &&
                this.x + this.width > paddle.getX() &&
                this.y < paddle.getY() + paddle.getHeight() &&
                this.y + this.height > paddle.getY();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PowerUp)) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            return true;
        }
        return false;
    }
}
