package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import org.example.demo.GameManager;
import org.example.demo.Renderer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameCompleteState implements GameState {
    boolean isHighScore;

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Game Complete State");
        ArrayList<Integer> scores = new ArrayList<>();
        int newScore = gameManager.getScore();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\OOP_Project\\OOP-GROUP4\\Arkanoid\\src\\main\\resources\\scores.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
        }
        if (scores.size() >= 10 && newScore <= scores.get(scores.size() - 1)) {
            isHighScore = false;
        } else {
            isHighScore = true;
            scores.add(newScore);
            scores.sort(Collections.reverseOrder());

            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("D:\\OOP_Project\\OOP-GROUP4\\Arkanoid\\src\\main\\resources\\scores.txt"))) {
                for (int i = 0; i < Math.min(10, scores.size()); i++) {
                    writer.write(String.valueOf(scores.get(i)));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
        if (event.getCode() == KeyCode.ENTER) {
            gameManager.restartGame();
            gameManager.getGameStateMachine().changeState(gameManager, new MenuState());
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

    public boolean isHighScore() {
        return isHighScore;
    }
}