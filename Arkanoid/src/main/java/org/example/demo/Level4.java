package org.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Level4 extends Level {
    public Level4() {
        super(4, "Diamond", "hard");
    }

    @Override
    protected List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int ROWS = 8;
        int COLS = 8;
        int centerRow = ROWS / 2;
        int centerCol = COLS / 2;
        for (int row = 0; row < ROWS; row++) {
            int distanceFromCenter = Math.abs(row - centerRow);
            int bricksInRow = COLS - 2 * distanceFromCenter;
            int startCol = distanceFromCenter;

            for (int col = startCol; col < startCol + bricksInRow; col++) {

                int x = col * Brick.brickWidth;
                int y = row * Brick.brickHeight;
                if (row == centerRow) {
                    bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else bricks.add(new NormalBrick(x, y, Brick.brickWidth, Brick.brickHeight));
            }
        }
        return bricks;
    }
}
