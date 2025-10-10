package org.example.demo.Levels;

import org.example.demo.Objects.Brick;

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

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public abstract List<Brick> createBricks();
}
