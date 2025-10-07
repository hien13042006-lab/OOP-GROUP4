package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelCompleteState implements GameState {

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Menu State");
        return true;
    }

      @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Menu State");
        return true;
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ENTER) {
            LevelManager levelManager = gameManager.getLevelManager();
            if (levelManager.hasNextLevel()) {
                levelManager.nextLevel();
                gameManager.getGameStateMachine().pushState(gameManager, new PlayingState());
            } else {
                gameManager.getGameStateMachine().pushState(gameManager, new GameCompleteState());
            }
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Không cập nhật gì khi level complete
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this,gameManager);
    }
}