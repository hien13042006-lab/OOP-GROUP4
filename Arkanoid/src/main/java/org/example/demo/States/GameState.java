package org.example.demo.States;

import javafx.scene.input.KeyEvent;
import org.example.demo.GameManager;
import org.example.demo.Renderer;

public interface GameState {
    void handleInput(KeyEvent event, GameManager gameManager);

    void update(double dt, GameManager gameManager);

    void render(Renderer renderer, GameManager gameManager);

    boolean enter(GameManager gameManager);

    boolean exit(GameManager gameManager);
}
