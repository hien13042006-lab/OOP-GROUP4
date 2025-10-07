package org.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Level2 extends Level {
    public Level2() {
        super(2,"Basic Grid" , "easy");
    }

    @Override
    protected List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int rows = 1;
        int cols = 8;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * brickWidth;
                int y = row * brickHeight + 50;

                if ((row + col) % 2 == 0) {
                    bricks.add(new NormalBrick(x, y, brickWidth, brickHeight));
                } else {
                    bricks.add(new StrongBrick(x, y, brickWidth, brickHeight));
                }
            }
        }
        return bricks;
    }
}
