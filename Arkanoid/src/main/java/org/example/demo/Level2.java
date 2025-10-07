package org.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Level2 extends Level {
    public Level2() {
        super(2, "Pyramid", "easy");
    }

    @Override
    protected List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int rows = 8;
        int cols = 8;

        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 5 + col; row++) {
                int x = col * Brick.brickWidth;
                int y = row * Brick.brickHeight;
                bricks.add(new NormalBrick(x, y, Brick.brickWidth, Brick.brickHeight));
            }
        }
        for (int col = 7; col >= 4; col--) {
            for (int row = 0; row < 12 - col; row++) {
                int x = col * Brick.brickWidth;
                int y = row * Brick.brickHeight;
                bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
            }
        }
        return bricks;
    }
}




