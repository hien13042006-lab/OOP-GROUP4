package org.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends Level {
    public Level3() {
        super(3, "Checkerboard", "hard");
    }

    @Override
    protected List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int ROWS = 8;
        int COLS = 8;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Chỉ tạo brick ở các ô checkered
                if ((row + col) % 2 == 0) {
                    int x = col * Brick.brickWidth;
                    int y = row * Brick.brickHeight;
                    bricks.add(new NormalBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        return bricks;
    }
}
