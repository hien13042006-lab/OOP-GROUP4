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
