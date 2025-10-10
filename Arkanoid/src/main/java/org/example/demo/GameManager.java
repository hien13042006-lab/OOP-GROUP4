package org.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import org.example.demo.Levels.Level;
import org.example.demo.Levels.LevelManager;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Brick;
import org.example.demo.Objects.Paddle;
import org.example.demo.Objects.PowerUp;
import org.example.demo.States.GameStateMachine;
import org.example.demo.States.MenuState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private Renderer renderer;
    private Paddle paddle;
    private Canvas canvas;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<PowerUp> activePowerUps; //các powerUp đang áp dụng
    private List<PowerUp> fallingPowerUps; //các powerUp đang rơi
    private GameStateMachine gameStateMachine; //quản lí state
    private LevelManager levelManager; // quản lí level

    private int score;
    private int lives;

    private Random rand = new Random(); // random

    void startGame(Group root) {
        // Khởi tạo game state
        score = 0;
        lives = 3;

        levelManager = new LevelManager();


        initializeCanvas(root);
        initializeLevel();

        // Bắt đầu với menu state
        gameStateMachine = new GameStateMachine();
        gameStateMachine.pushState(this, new MenuState());

    }

    private void initializeCanvas(Group root) {
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        canvas.setFocusTraversable(true); //Cho phép Canvas có thể nhận focus

        // Thiết lập input handler
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameStateMachine.handleInput(event, GameManager.this);
            }
        });

        root.getChildren().add(canvas);
        canvas.requestFocus(); //Yêu cầu lấy focus ngay lập tức

        GraphicsContext gc = canvas.getGraphicsContext2D();
        renderer = new Renderer(gc);
    }

    public void initializeLevel() {

        Level currentLevel = levelManager.getCurrentLevel();

        paddle = new Paddle((WINDOW_WIDTH - Paddle.PADDLE_WIDTH) / 2,
                WINDOW_HEIGHT - Paddle.PADDLE_HEIGHT - Paddle.MARGIN_BOTTOM,
                Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT, 0, 0, 800);

        // THONG SO BALL
        balls = new ArrayList<>();
        balls.add(new Ball(WINDOW_WIDTH / 2 - Ball.RADIUS, paddle.getY() - Ball.RADIUS * 2,
                Ball.RADIUS * 2, Ball.RADIUS * 2, 1, 1, Ball.SPEED));

        bricks = currentLevel.createBricks();

        //powerUp
        activePowerUps = new ArrayList<>();// các powerUp đang rơi
        fallingPowerUps = new ArrayList<>();// các powerUp đang tác dụng

        // Thiết lập input handler cho paddle
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameStateMachine.handleInput(event, GameManager.this);
            }
        });
        canvas.setOnKeyReleased(paddle.getKeyReleaseHandler());
    }


    void updateGame(double dt) {
        gameStateMachine.update(this, dt);
    }

    void render() {
        gameStateMachine.render(this, renderer);
    }


    // Game management methods
    public void addScore(int points) {
        this.score += points;
    }

    public void loseLife() {
        this.lives--;
    }

    public void resetBallAndPaddle() {
        paddle.setX((WINDOW_WIDTH - paddle.getWidth()) / 2);
        balls.add(new Ball(WINDOW_WIDTH / 2 - Ball.RADIUS, paddle.getY() - Ball.RADIUS * 2,
                Ball.RADIUS * 2, Ball.RADIUS * 2, 1, 1, Ball.SPEED));
    }

    public void restartGame() {
        score = 0;
        lives = 3;
        levelManager.resetLevel();
    }


    // Getters
    public Paddle getPaddle() {
        return paddle;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public List<PowerUp> getFallingPowerUps() {
        return fallingPowerUps;
    }

    public List<PowerUp> getActivePowerUps() {
        return activePowerUps;
    }

    public Random getRand() {
        return rand;
    }

    public GameStateMachine getGameStateMachine() {
        return gameStateMachine;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
}