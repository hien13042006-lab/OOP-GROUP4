package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import org.example.demo.GameManager;
import org.example.demo.Objects.GameObject;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

public class MenuState implements GameState {

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Menu State");
        GameManager.soundManager.playSoundEffect("BackgroundSoundtrack");
        return true;
    }

    @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Menu State");
        GameManager.soundManager.stopSoundEffect("BackgroundSoundtrack");
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
    public void update(double dt, GameManager gameManager) {
        // Menu không cần update logic vật lý
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
    }
}