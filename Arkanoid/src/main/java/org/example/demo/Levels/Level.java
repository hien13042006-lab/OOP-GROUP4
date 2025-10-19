package org.example.demo.Levels;

import org.example.demo.Objects.Bricks.Brick;

import java.util.List;

public abstract class Level {
    protected String difficulty;
    protected String levelName;
    protected int levelNumber;

    public Level(int levelNumber, String levelName, String difficulty) {
        this.levelNumber = levelNumber;
        this.levelName = levelName;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public abstract List<Brick> createBricks();
}
