package org.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends Level {

    public Level1() {
        super(1,"Basic Grid" , "easy");
    }

    @Override
    protected List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int rows = 5;
        int cols = 8;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * Brick.brickWidth;
                int y = row * Brick.brickHeight;

                if ((row + col) % 2 == 0) {
                    bricks.add(new MovingBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else {
                    bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        return bricks;
    }
}
