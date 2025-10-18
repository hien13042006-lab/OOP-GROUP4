package org.example.demo.Levels;

import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.*;


import java.util.ArrayList;
import java.util.List;

public class Level1 extends Level {

    public Level1() {
        super(1,"Basic Grid" , "easy");
    }

    @Override
    public List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int rows = 5;
        int cols = 13;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * Brick.brickWidth;
                int y = 50 + row * Brick.brickHeight;

                if ((row + col) % 2 == 0) {
                    bricks.add(new ExplodeBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else {
                    bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        return bricks;
    }
}
