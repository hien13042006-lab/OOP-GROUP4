package org.example.demo.Levels;

import java.util.ArrayList;
import java.util.List;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.ExplodeBrick;
import org.example.demo.Objects.Bricks.MovingBrick;
import org.example.demo.Objects.Bricks.StrongBrick;
import org.example.demo.Objects.Bricks.UnbreakableBrick;

public class Level5 extends Level {

    public Level5() {
        super(5, "hard");
    }

    @Override
    public List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int ROWS = 10;
        int COLS = 13;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = col * Brick.brickWidth;
                int y = 50 + row * Brick.brickHeight;
                if (row == 0 || (row == ROWS - 1 && col != COLS / 2) || col == 0
                        || col == COLS - 1) {
                    bricks.add(new UnbreakableBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else if (row == 1 || row == ROWS - 2 || col == 1 || col == COLS - 2) {
                    bricks.add(new ExplodeBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else if (col != COLS / 2 || row != ROWS - 1) {
                    bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        for(int col = 0; col < COLS; col++) {
            int x = col * Brick.brickWidth;
            int y = 50 + ROWS * Brick.brickHeight;
            bricks.add(new MovingBrick(x, y, Brick.brickWidth, Brick.brickHeight));
        }
        return bricks;
    }
}
