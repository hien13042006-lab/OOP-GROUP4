package org.example.demo;

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
import org.example.demo.States.ScoreState;

public class Renderer {

    private GraphicsContext gc;
    private Image background = new Image(getClass().getResourceAsStream("/asset/background.png"));
    private Image gameOver = new Image(getClass().getResourceAsStream("/asset/gameover.png"));
    private Image ScoreState = new Image(getClass().getResourceAsStream("/asset/ScoreState.png"));

    public Renderer(GraphicsContext gc) {
        this.gc = gc;

    }

    public GraphicsContext getGc() {
        return gc;
    }

    //Xoa man hinh
    public void clear(double width, double height) {
        gc.drawImage(background, 0, 0, width, height);
    }

    //draw gameObject
    public void draw(GameObject object, Image image) {
        gc.drawImage(image, object.getX(), object.getY(), object.getWidth(), object.getHeight());
    }


    public void draw(MenuState menu, GameManager gameManager) {
        // Vẽ nền
        clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        // Vẽ tiêu đề
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                80));
        gc.setFill(Color.PINK);
        gc.fillText("ARKANOID",
                GameManager.WINDOW_WIDTH / 2 - 270,
                GameManager.WINDOW_HEIGHT / 2 - 50);
    }

    public void draw(GameOverState gameOverState, GameManager gameManager) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.drawImage(gameOver, 0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                24));
        if (gameOverState.isHighScore()) {
            gc.fillText("High Score  " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 125,
                    GameManager.WINDOW_HEIGHT / 2 + 270);
        } else {
            gc.fillText("Final Score  " + gameManager.getScore(),
                    GameManager.WINDOW_WIDTH / 2 - 125,
                    GameManager.WINDOW_HEIGHT / 2 + 270);
        }
//        gc.fillText("Press ENTER for Main Menu", GameManager.WINDOW_WIDTH / 2 - 140,
//                GameManager.WINDOW_HEIGHT / 2 + 300);
    }

    public void draw(PlayingState playingState, GameManager gameManager) {
        this.clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);
        gameManager.getPaddle().render(this);
        for (Ball ball : gameManager.getBalls()) {
            ball.render(this);
        }
        for (Brick brick : gameManager.getBricks()) {
            brick.render(this);
        }
        for (PowerUp powerUp : gameManager.getFallingPowerUps()) {
            powerUp.render(this);
        }
        // Vẽ HUD
        drawHUD(this, gameManager);
    }

    private void drawHUD(Renderer renderer, GameManager gameManager) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, 50);

        gc.setFill(Color.WHITE);
        Font fontHUD = Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                30
        );
        gc.setFont(fontHUD);

        gc.fillText("Score " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 15, 30);

        Image lives = new Image(getClass().getResourceAsStream("/asset/life.png"));
        for (int i = 0; i < gameManager.getLives(); i++) {
            gc.drawImage(lives, 30 * i, 10, 30, 30);
        }

        gc.fillText("Level " + gameManager.getLevelManager().getCurrentNumberLevel(), 150, 30);
        gc.fillText(gameManager.getLevelManager().getCurrentLevel().getDifficulty(), 800, 30);
    }

    public void draw(PausedState pausedState, GameManager gameManager) {
        // Vẽ game ở trạng thái hiện tại (để làm nền)
        PlayingState playingState = new PlayingState();
        playingState.render(this, gameManager);

        // Vẽ overlay tạm dừng
        gc.setFill(Color.rgb(0, 0, 0, 0.7));
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                36));
        gc.fillText("PAUSED", GameManager.WINDOW_WIDTH / 2 - 95, GameManager.WINDOW_HEIGHT / 2);


    }

    public void draw(LevelCompleteState levelCompleteState, GameManager gameManager) {
        clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        gc.setFill(Color.GREEN);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                48));
        gc.fillText("LEVEL COMPLETE!", GameManager.WINDOW_WIDTH / 2 - 270,
                GameManager.WINDOW_HEIGHT / 2 - 100);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                24));
        gc.fillText("Score  " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 70,
                GameManager.WINDOW_HEIGHT / 2 - 50);
//        gc.fillText("Press ENTER for Next Level", GameManager.WINDOW_WIDTH / 2 - 176,
//                GameManager.WINDOW_HEIGHT / 2 + 80);
    }

    public void draw(GameCompleteState gameCompleteState, GameManager gameManager) {
        GraphicsContext gc = this.getGc();

        clear(GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);

        // Tiêu đề
        gc.setFill(Color.GREEN);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                48));
        gc.fillText("CONGRATULATIONS!",
                GameManager.WINDOW_WIDTH / 2 - 300,
                GameManager.WINDOW_HEIGHT / 2 - 150);

        // Thông báo
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                36));
        gc.fillText("You have completed all levels!",
                GameManager.WINDOW_WIDTH / 2 - 400,
                GameManager.WINDOW_HEIGHT / 2 - 100);

        // Điểm số
        gc.setFill(Color.WHITE);
        gc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                24));
        if (gameCompleteState.isHighScore()) {
            gc.fillText("High Score  " + gameManager.getScore(), GameManager.WINDOW_WIDTH / 2 - 120,
                    GameManager.WINDOW_HEIGHT / 2 + 60);
        } else {
            gc.fillText("Final Score  " + gameManager.getScore(),
                    GameManager.WINDOW_WIDTH / 2 - 120,
                    GameManager.WINDOW_HEIGHT / 2 + 60);
        }

        // Hướng dẫn
//        gc.fillText("Press ENTER to return to Main Menu",
//                GameManager.WINDOW_WIDTH / 2 - 200,
//                GameManager.WINDOW_HEIGHT / 2 + 120);
    }

    public void draw(ScoreState scoreState, GameManager gameManager) {
        gc.setFill(Color.CYAN);
        gc.fillRect(0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);
        gc.drawImage(ScoreState, 0, 0, GameManager.WINDOW_WIDTH, GameManager.WINDOW_HEIGHT);
        Font BoldPixels70 = Font.loadFont(
                getClass().getResourceAsStream("/asset/fontHUD.ttf"),
                70
        );

        gc.setFont(BoldPixels70);
        gc.setFill(Color.rgb(104, 198, 224));
        gc.fillText("HIGH SCORE", GameManager.WINDOW_WIDTH / 2 - 250, 150);
        Font BoldPixels48 = Font.font(BoldPixels70.getName(), 48);
        gc.setFont(BoldPixels48);
        for (int i = 0; i < scoreState.getScores().size(); i++) {
            gc.fillText(String.format("%d: %s", i + 1, scoreState.getScores().get(i)),
                    GameManager.WINDOW_WIDTH / 2 - 80, 230 + i * 40);
        }
    }
}
