package org.example.demo.Levels;

import java.util.List;
import org.example.demo.Objects.Bricks.Brick;

public abstract class Level {

    protected String difficulty;
    protected int levelNumber;

    public Level(int levelNumber, String difficulty) {
        this.levelNumber = levelNumber;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public abstract List<Brick> createBricks();
}
