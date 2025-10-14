package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import org.example.demo.GameManager;
import org.example.demo.Objects.GameObject;
import org.example.demo.Renderer;

public class GameOverState implements GameState {

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Game Complete State");
        GameObject.soundManager.stopSoundEffect("BackgroundSoundtrack");
        return true;
    }

    @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Game Complete State");
        return true;
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ENTER) {
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState());
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Không cập nhật gì khi game over
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
    }
}