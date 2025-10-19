package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import org.example.demo.GameManager;
import org.example.demo.Renderer;

public class PausedState implements GameState {

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Menu State");
        return true;
    }

    @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Paused State");
        return true;
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ENTER) {
            gameManager.getGameStateMachine().popState(gameManager);
        } else  if (event.getCode() == KeyCode.ESCAPE) {
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState() );
        }
    }

    @Override
    public void handleMouseInput(MouseEvent event, GameManager gameManager) {

    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Không cập nhật gì khi tạm dừng
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
    }
}