package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import org.example.demo.GameManager;
import org.example.demo.Objects.Button.Button;
import org.example.demo.Objects.Button.HomeButton;
import org.example.demo.Objects.Button.LeaderboardButton;
import org.example.demo.Objects.Button.PlayButton;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScoreState implements GameState {

    private ArrayList<Integer> scores = new ArrayList<>();

    private Button homeButton = new HomeButton(GameManager.WINDOW_WIDTH / 2 - 75,
           GameManager.WINDOW_HEIGHT / 2 + 230, 150, 60);

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Game Complete State");
        File scoreFile = new File("/datas/scores.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Game Complete State");
        return true;
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
    }

    @Override
    public void handleMouseInput(MouseEvent event, GameManager gameManager) {
        if (homeButton.isClicked(event)) {
            SoundManager.playSoundEffect("SoundClick");
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState());
            System.out.println("moi");
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        homeButton.update(gameManager);
    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        renderer.draw(this, gameManager);
        homeButton.render(renderer);
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }
}