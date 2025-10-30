package org.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT, Color.BLACK);

        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame(root);

        double maxDeltaTime = 0.25;

        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime > 0) {
                    double dt = (now - lastTime) / 1e9; // giây
                    //giới hạn dt tránh bị giật
                    if(dt > maxDeltaTime) {
                        dt = maxDeltaTime;
                    }
                    gameManager.updateGame(dt);
                    gameManager.render();
                }
                lastTime = now;
            }
        };
        gameLoop.start();
    }
}