package org.example.demo;

import java.util.ArrayList;
import java.util.List;
public class LevelManager {
    private int currentNumberLevel;
    private List<Level>  levels;

    public LevelManager() {
        this.currentNumberLevel = 1;
        this.levels = new ArrayList<>();
        initializeLevels();
    }

    private void initializeLevels(){
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
    }

    public Level getCurrentLevel() {
        if (currentNumberLevel - 1 < levels.size()) {
            return levels.get(currentNumberLevel - 1);
        }
        return levels.get(levels.size() - 1); // Return last level nếu vượt quá
    }

    public void nextLevel() {
        currentNumberLevel++;
    }

    public boolean hasNextLevel() {
        return currentNumberLevel < levels.size();
    }

    public int getCurrentNumberLevel() {
        return currentNumberLevel;
    }

    public void resetLevel() {
        currentNumberLevel = 1;
    }
}
