package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.security.PublicKey;

public class Renderer {
    private GraphicsContext gc;

    public Renderer(GraphicsContext gc) {
        this.gc = gc;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    //Xoa man hinh
    public void clear(double width, double height) {
        gc.setFill(Color.BLACK); // nền
        gc.fillRect(0, 0, width, height);
    }

    //draw paddle
    public void draw(Paddle paddle) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    //draw ball
    public void draw(Ball ball) {
        gc.setFill(Color.BLUE);
        gc.fillOval(ball.x, ball.y, ball.width, ball.height);
    }
    //draw brick
    public void draw(StrongBrick brick){
        gc.setFill(Color.ORANGE);
        gc.fillRect(brick.x, brick.y,brick.width, brick.height);
    }

    public void draw(NormalBrick brick){
        gc.setFill(Color.RED);
        gc.fillRect(brick.x, brick.y,brick.width, brick.height);
    }
    public void draw(MenuState menu, GameManager gameManager){
        // Vẽ nền
        clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        // Vẽ tiêu đề
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 36));
        gc.fillText("BREAKOUT GAME",
                GameManager.WINDOW_WIDTH / 2 - 150,
                GameManager.WINDOW_HEIGHT / 2 - 50);

        // Vẽ hướng dẫn
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Press ENTER to Start",
                GameManager.WINDOW_WIDTH / 2 - 120,
                GameManager.WINDOW_HEIGHT / 2 + 20);
        gc.fillText("A/D to Move, ESC to Pause",
                GameManager.WINDOW_WIDTH / 2 - 140,
                GameManager.WINDOW_HEIGHT / 2 + 60);
    }
    public void draw(GameOverState gameOverState,GameManager gameManager){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 36));
        gc.fillText("GAME OVER", GameManager.WINDOW_WIDTH / 2 - 100, GameManager.WINDOW_HEIGHT / 2 - 50);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Final Score: " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 80, GameManager.WINDOW_HEIGHT / 2);
        gc.fillText("Press ENTER for Main Menu", GameManager.WINDOW_WIDTH / 2 - 140, GameManager.WINDOW_HEIGHT / 2 + 50);
    }

    public void draw(PlayingState playingState,GameManager gameManager){
        this.clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);
        gameManager.getPaddle().render(this);
        gameManager.getBall().render(this);
        for (Brick brick : gameManager.getBricks()) {
            brick.render(this);
        }

        // Vẽ HUD
        drawHUD(this, gameManager);
    }
    private void drawHUD(Renderer renderer, GameManager gameManager) {
        GraphicsContext gc = renderer.getGc();
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 16));
        gc.fillText("Score: " + gameManager.getScore(), 10, 20);
        gc.fillText("Lives: " + gameManager.getLives(), 10, 40);
        gc.fillText("Level: " + gameManager.getCurrentLevel(), 10, 60);
    }
    public void draw(PausedState pausedState,GameManager gameManager){
        // Vẽ game ở trạng thái hiện tại (để làm nền)
        PlayingState playingState = new PlayingState();
        playingState.render(this, gameManager);

        // Vẽ overlay tạm dừng
        gc.setFill(Color.rgb(0, 0, 0, 0.7));
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 36));
        gc.fillText("PAUSED", GameManager.WINDOW_WIDTH / 2 - 80, GameManager.WINDOW_HEIGHT / 2);

        gc.setFont(new Font("Arial", 18));
        gc.fillText("Press ESC to Resume", GameManager.WINDOW_WIDTH / 2 - 100, GameManager.WINDOW_HEIGHT / 2 + 40);
    }
    public void draw (LevelCompleteState levelCompleteState, GameManager gameManager){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Arial", 36));
        gc.fillText("LEVEL COMPLETE!", GameManager.WINDOW_WIDTH / 2 - 150, GameManager.WINDOW_HEIGHT / 2 - 50);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Score: " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 50, GameManager.WINDOW_HEIGHT / 2);
        gc.fillText("Press ENTER for Next Level", GameManager.WINDOW_WIDTH / 2 - 140, GameManager.WINDOW_HEIGHT / 2 + 50);
    }
}
