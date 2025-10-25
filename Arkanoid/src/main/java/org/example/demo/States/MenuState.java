package org.example.demo.States;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.demo.GameManager;
import org.example.demo.Objects.Button.Button;
import org.example.demo.Objects.Button.LeaderboardButton;
import org.example.demo.Objects.Button.PlayButton;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

public class MenuState implements GameState {

    private Button playButton = new PlayButton(GameManager.WINDOW_WIDTH / 2 - 75,
            GameManager.WINDOW_HEIGHT / 2 - 10, 150, 60);
    private Button leaderboardButton = new LeaderboardButton(GameManager.WINDOW_WIDTH / 2 - 75,
            GameManager.WINDOW_HEIGHT / 2 + 50, 150, 60);

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
            gameManager.getGameStateMachine().changeState(gameManager, new PlayingState());
        } else if (event.getCode() == KeyCode.S) {
            gameManager.getGameStateMachine().changeState(gameManager, new ScoreState());
        }
    }

    @Override
    public void handleMouseInput(MouseEvent event, GameManager gameManager) {
        if (playButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.getGameStateMachine().changeState(gameManager, new PlayingState());
            System.out.println("moi");
        }
        if(leaderboardButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.getGameStateMachine().changeState(gameManager, new ScoreState());
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Menu không cần update logic vật lý
        playButton.update(gameManager);
        leaderboardButton.update(gameManager);
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
        playButton.render(renderer);
        leaderboardButton.render(renderer);
    }
}