package org.example.demo;

import java.util.List;

public abstract class Level {
    public static final int brickWidth = GameManager.WINDOW_WIDTH / 8;
    int brickHeight = 30;

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

    protected abstract List<Brick> createBricks();
}
