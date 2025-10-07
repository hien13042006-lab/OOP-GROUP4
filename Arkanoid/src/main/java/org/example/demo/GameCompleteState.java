package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameCompleteState implements GameState {

    @Override
    public void enter(GameManager gameManager) {
        System.out.println("Entering Game Complete State");
    }

    @Override
    public void exit(GameManager gameManager) {
        System.out.println("Exiting Game Complete State");
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ENTER) {
            gameManager.restartGame();
            gameManager.setState(new MenuState());
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Không cập nhật gì khi game complete
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
    }
}