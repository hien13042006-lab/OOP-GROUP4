package org.example.demo;

import javafx.scene.input.KeyEvent;

public interface GameState {
    void handleInput(KeyEvent event, GameManager gameManager);
    void update(double dt, GameManager gameManager);
    void render(Renderer renderer, GameManager gameManager);
    void enter(GameManager gameManager);
    void exit(GameManager gameManager);
}
