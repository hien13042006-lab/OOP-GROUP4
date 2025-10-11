package org.example.demo.Levels;

import java.util.ArrayList;
import java.util.List;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.MovingBrick;
import org.example.demo.Objects.Bricks.NormalBrick;
import org.example.demo.Objects.Bricks.StrongBrick;
import org.example.demo.Objects.Bricks.UnbreakableBrick;

public class Level4 extends Level {

    public Level4() {
        super(4, "Diamond", "hard");
    }

    @Override
    public List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int ROWS = 11;
        int COLS = 13;
        int centerRow = ROWS / 2;
        int centerCol = COLS / 2;
        for (int row = 0; row < ROWS; row++) {
            int distanceFromCenter = Math.abs(row - centerRow);
            int bricksInRow = COLS - 2 * distanceFromCenter;
            int startCol = distanceFromCenter;

            for (int col = startCol; col < startCol + bricksInRow; col++) {

                int x = col * Brick.brickWidth;
                int y = 50 + row * Brick.brickHeight;
                if (row == 0 || row == ROWS - 1) {
                    bricks.add(new UnbreakableBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
                else if (row == centerRow) {
                    bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                } else {
                    bricks.add(new NormalBrick(x, y, Brick.brickWidth, Brick.brickHeight));
                }
            }
        }
        return bricks;
    }
}
