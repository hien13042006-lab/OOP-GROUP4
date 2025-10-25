package org.example.demo.States;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.demo.GameManager;
import org.example.demo.Objects.Button.BackButton;
import org.example.demo.Objects.Button.Button;
import org.example.demo.Objects.Button.HomeButton;
import org.example.demo.Objects.Button.RestartButton;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

public class PausedState implements GameState {

    private Button restartButton = new RestartButton(GameManager.WINDOW_WIDTH / 2 - 75,
            GameManager.WINDOW_HEIGHT / 2 + 70, 150, 60);
    private Button homeButton = new HomeButton(GameManager.WINDOW_WIDTH / 2 - 75,
            GameManager.WINDOW_HEIGHT / 2 + 130, 150, 60);
    private Button backButton = new BackButton(GameManager.WINDOW_WIDTH / 2 - 75,
            GameManager.WINDOW_HEIGHT / 2 +10, 150, 60);

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
        } else if (event.getCode() == KeyCode.ESCAPE) {
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState());
        }
    }

    @Override
    public void handleMouseInput(MouseEvent event, GameManager gameManager) {
        if (restartButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new PlayingState());
        } else if (homeButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState());
        } else if (backButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.getGameStateMachine().popState(gameManager);
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        homeButton.update(gameManager);
        restartButton.update(gameManager);
        backButton.update(gameManager);
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
        homeButton.render(renderer);
        restartButton.render(renderer);
        backButton.render(renderer);
    }
}