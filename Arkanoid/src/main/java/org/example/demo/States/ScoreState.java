package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import org.example.demo.GameManager;
import org.example.demo.Objects.GameObject;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreState implements GameState {

    private ArrayList<Integer> scores = new ArrayList<>();

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Game Complete State");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\OOP_Project\\OOP-GROUP4\\Arkanoid\\src\\main\\resources\\scores.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
        }
        SoundManager.stopSoundEffect("BackgroundSoundtrack");
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

    public ArrayList<Integer> getScores() {
        return scores;
    }
}