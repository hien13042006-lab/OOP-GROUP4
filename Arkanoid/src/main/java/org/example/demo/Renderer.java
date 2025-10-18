package org.example.demo;

import java.awt.Button;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.GameObject;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.States.GameCompleteState;
import org.example.demo.States.GameOverState;
import org.example.demo.States.LevelCompleteState;
import org.example.demo.States.MenuState;
import org.example.demo.States.PausedState;
import org.example.demo.States.PlayingState;

public class Renderer {

    private GraphicsContext gc;
    private Image background;
    private Button btm = new Button("Start");

    public Renderer(GraphicsContext gc) {
        this.gc = gc;
        background = new Image(getClass().getResourceAsStream("/asset/background.png"));
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    //Xoa man hinh
    public void clear(double width, double height) {
        gc.drawImage(background, 0, 0, width, height);
    }

    //draw gameObject
    public void draw(GameObject object, Image image) {
        gc.drawImage(image, object.getX(), object.getY(), object.getWidth(), object.getHeight());
    }


    public void draw(MenuState menu, GameManager gameManager){
        // Vẽ nền
        clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        // Vẽ tiêu đề
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 36));
        gc.fillText("ARKANOID GAME",
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
    public void draw(GameOverState gameOverState, GameManager gameManager){
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

    public void draw(PlayingState playingState, GameManager gameManager){
        this.clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);
        gameManager.getPaddle().render(this);
        for(Ball ball : gameManager.getBalls()) {
            ball.render(this);
        }
        for (Brick brick : gameManager.getBricks()) {
            brick.render(this);
        }
        for(PowerUp powerUp : gameManager.getFallingPowerUps()) {
            powerUp.render(this);
        }
        // Vẽ HUD
        drawHUD(this, gameManager);
    }
    private void drawHUD(Renderer renderer, GameManager gameManager) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, 50 );

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 30));
        gc.fillText("Score: " + gameManager.getScore(), GameManager.WINDOW_WIDTH/2 - 15, 30);
        gc.fillText("Lives: " + gameManager.getLives(), 10, 30);
        gc.fillText("Level: " + gameManager.getLevelManager().getCurrentNumberLevel(), 150, 30);
        gc.fillText("Difficulty: "+ gameManager.getLevelManager().getCurrentLevel().getDifficulty(),840,30);
    }
    public void draw(PausedState pausedState, GameManager gameManager){
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
        gc.fillText("Press ENTER to Resume", GameManager.WINDOW_WIDTH / 2 - 100, GameManager.WINDOW_HEIGHT / 2 + 40);
        gc.fillText("Press ESC to Play again",GameManager.WINDOW_WIDTH / 2 - 100, GameManager.WINDOW_HEIGHT / 2 + 90);

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

    public void draw(GameCompleteState gameCompleteState, GameManager gameManager) {
        GraphicsContext gc = this.getGc();

        // Nền vàng
        gc.setFill(Color.GOLD);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        // Tiêu đề
        gc.setFill(Color.DARKBLUE);
        gc.setFont(new Font("Arial", 48));
        gc.fillText("CHÚC MỪNG!",
                GameManager.WINDOW_WIDTH / 2 - 150,
                GameManager.WINDOW_HEIGHT / 2 - 100);

        // Thông báo
        gc.setFont(new Font("Arial", 36));
        gc.fillText("Bạn đã hoàn thành tất cả level!",
                GameManager.WINDOW_WIDTH / 2 - 250,
                GameManager.WINDOW_HEIGHT / 2);

        // Điểm số
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Điểm cuối cùng: " + gameManager.getScore(),
                GameManager.WINDOW_WIDTH / 2 - 100,
                GameManager.WINDOW_HEIGHT / 2 + 60);

        // Hướng dẫn
        gc.fillText("Nhấn ENTER để về Menu",
                GameManager.WINDOW_WIDTH / 2 - 120,
                GameManager.WINDOW_HEIGHT / 2 + 120);
    }
}
