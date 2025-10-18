package org.example.demo.Objects.Bricks;

import java.security.PublicKey;

import javafx.scene.image.Image;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Objects.PowerUps.ReverseControlPowerUp;
import org.example.demo.Objects.PowerUps.SplitBallPowerUp;
import org.example.demo.Renderer;

import java.util.List;
import java.util.Random;

public class ExplodeBrick extends Brick {
    int DROP_CHANCE_STRONG_BRICK = 50;
    int SPLIT_BALL_DROP_CHANCE = 30;
    int REVERSE_CONTROL_DROP_CHANCE = 20;
    private boolean exploded = false;

    public ExplodeBrick(double x, double y, double width, double height) {
        this.hitPoints = 1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(getClass().getResourceAsStream("/asset/ExplodeBrick.png"));
        powerUpDropChance = DROP_CHANCE_STRONG_BRICK;
    }

    public ExplodeBrick() {
        this.hitPoints = 1;
        image = new Image(getClass().getResourceAsStream("/asset/ExplodeBrick.png"));
    }


    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    @Override
    public PowerUp makePowerUp() {
        Random rand = new Random();
        int chance = rand.nextInt(powerUpDropChance);
        if (chance < SPLIT_BALL_DROP_CHANCE) {
            return new SplitBallPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else return new ReverseControlPowerUp(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void takeHit(int damage, List<Brick> bricks) {
        if (exploded) return;
        this.hitPoints -= damage;
        if (this.hitPoints <= 0) {
            exploded = true;
            for (int i = bricks.size() - 1; i >= 0; i--) {
                Brick brick = bricks.get(i);
                if (brick instanceof ExplodeBrick) {
                    ExplodeBrick ex = (ExplodeBrick) brick;
                    if (ex.exploded) continue;
                }
                if ((brick.getY() == this.getY() && Math.abs(brick.getX() - this.getX()) == Brick.brickWidth) ||
                        (brick.getX() == this.getX() && Math.abs(brick.getY() - this.getY()) == Brick.brickHeight)) {
                    brick.takeHit(1, bricks);
                }
            }
        }
    }

    @Override
    public void update(double dt) {
        return;
    }
}
