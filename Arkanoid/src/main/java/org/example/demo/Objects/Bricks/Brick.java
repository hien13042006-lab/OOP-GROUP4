package org.example.demo.Objects.Bricks;

import javafx.scene.image.Image;
import org.example.demo.GameManager;
import org.example.demo.Objects.GameObject;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Renderer;

public class Brick extends GameObject {
    public static final int brickWidth = GameManager.WINDOW_WIDTH / 8;
    public static final int brickHeight = 30;

    protected Image threePoint = new Image(getClass().getResourceAsStream("/asset/movingBrick.png"));
    protected Image twoPoint = new Image(getClass().getResourceAsStream("/asset/strongBrick.png"));
    protected Image onePoint = new Image(getClass().getResourceAsStream("/asset/normalBrick.png"));

    protected int hitPoints;
    //ti le roi ra powerUp (theo %)
    protected int powerUpDropChance;

    public void takeHit(int damage) {
        hitPoints-=damage;
    }

    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    @Override
    public void update(double dt) {
        if(hitPoints == 3)
        {
            image = threePoint;
        } else if(hitPoints == 2)
        {
            image = twoPoint;
        }
        else{
            image = onePoint;
        }
    }

    @Override
    public void render(Renderer r) {
    }

    public PowerUp makePowerUp() {
        return null;
    }

    public double getPowerUpDropChance() {
        return powerUpDropChance;
    }

    public int getHitPoints() {
        return hitPoints;
    }
}
