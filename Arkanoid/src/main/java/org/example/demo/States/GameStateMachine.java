package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import org.example.demo.GameManager;
import org.example.demo.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameStateMachine {
    List<GameState> gameStateList = new ArrayList<>();

    public void pushState(GameManager gameManager, GameState state) {
        gameStateList.add(state);
        gameStateList.getLast().enter(gameManager);
    }

    public void popState(GameManager gameManager) {
        if (!gameStateList.isEmpty()) {
            if (gameStateList.getLast().exit(gameManager)) {
                gameStateList.remove(gameStateList.getLast());
            }
        }
    }

    public void changeState(GameManager gameManager, GameState state) {
        if (!gameStateList.isEmpty()) {
            if (gameStateList.getLast().exit(gameManager)) {
                gameStateList.remove(gameStateList.getLast());
            }
        }

        gameStateList.add(state);
        gameStateList.getLast().enter(gameManager);
    }

    public void update(GameManager gameManager, double dt) {
        if (!gameStateList.isEmpty()) {
            gameStateList.getLast().update(dt, gameManager);
        }
    }


    public void render(GameManager gameManager, Renderer renderer) {
        if (!gameStateList.isEmpty()) {
            gameStateList.getLast().render(renderer, gameManager);
        }
    }

    public void handleInput(KeyEvent event, GameManager gameManager)
    {
        if (!gameStateList.isEmpty()) {
            gameStateList.getLast().handleInput(event, gameManager);
        }
    }

    public List<GameState> getGameStateList() {
        return gameStateList;
    }
}
