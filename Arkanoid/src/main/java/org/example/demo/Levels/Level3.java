package org.example.demo.Levels;

import java.util.ArrayList;
import java.util.List;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.MovingBrick;

public class Level3 extends Level {

    public Level3() {
        super(3, "Checkerboard", "hard");
    }

    @Override
    public List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int ROWS = 8;
        int COLS = 13;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Chỉ tạo brick ở các ô checkered
                if ((row + col) % 2 == 0) {
                    int x = col * Brick.brickWidth;
                    int y = 50 + row * Brick.brickHeight;
                    bricks.add(new MovingBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        return bricks;
    }
}
