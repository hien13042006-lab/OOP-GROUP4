package org.example.demo.Levels;

import java.util.ArrayList;
import java.util.List;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.NormalBrick;
import org.example.demo.Objects.Bricks.StrongBrick;
import org.example.demo.Objects.Bricks.UnbreakableBrick;

public class Level2 extends Level {

    public Level2() {
        super(2, "Pyramid", "easy");
    }

    @Override
    public List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int rows = 8;
        int cols = 13;
        int minBricks = 3;
        int maxBricks = cols + minBricks;

        for (int col = 0; col < cols / 2; col++) {
            for (int row = 0; row <= minBricks + col + 1; row++) {
                int x = col * Brick.brickWidth;
                int y = 50 + row * Brick.brickHeight;
                bricks.add(new NormalBrick(x, y, Brick.brickWidth, Brick.brickHeight));
            }
        }
        for (int col = cols - 1; col > cols / 2; col--) {
            for (int row = 0; row <= maxBricks - col; row++) {
                int x = col * Brick.brickWidth;
                int y = 50 + row * Brick.brickHeight;
                bricks.add(new StrongBrick(x, y, Brick.brickWidth, Brick.brickHeight));
            }
        }
        int mid = cols / 2;
        for (int row = 0; row <= maxBricks - mid; row++) {
            int x = mid * Brick.brickWidth;
            int y = 50 + row * Brick.brickHeight;
            bricks.add(new UnbreakableBrick(x, y, Brick.brickWidth, Brick.brickHeight));
        }
        return bricks;
    }
}




