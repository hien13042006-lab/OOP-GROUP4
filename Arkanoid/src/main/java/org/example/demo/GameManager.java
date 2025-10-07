package org.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private Renderer renderer;
    private Paddle paddle;
    private Canvas canvas;
    private Ball ball;
    private List<Brick> bricks;
    private List<PowerUp> activePowerUps; //các powerUp đang áp dụng
    private List<PowerUp> fallingPowerUps; //các powerUp đang rơi
    private GameState currentState;
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
        currentState = new MenuState();
        currentState.enter(this);
    }

    private void initializeCanvas(Group root) {
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        canvas.setFocusTraversable(true); //Cho phép Canvas có thể nhận focus

        // Thiết lập input handler
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentState.handleInput(event, GameManager.this);
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

        ball = new Ball(WINDOW_WIDTH / 2 - Ball.RADIUS, paddle.getY() - Ball.RADIUS * 2,
                Ball.RADIUS * 2, Ball.RADIUS * 2, 1, 1, 500);

        bricks= currentLevel.createBricks();

        //powerUp
        activePowerUps = new ArrayList<>();// các powerUp đang rơi
        fallingPowerUps = new ArrayList<>();// các powerUp đang tác dụng

        // Thiết lập input handler cho paddle
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentState.handleInput(event, GameManager.this);
            }
        });
        canvas.setOnKeyReleased(paddle.getKeyReleaseHandler());
    }


    void updateGame(double dt) {
        currentState.update(dt, this);
    }

    void render() {
        currentState.render(renderer, this);
    }

    // State management
    public void setState(GameState newState) {
        if (currentState != null) {
            currentState.exit(this);
        }
        currentState = newState;
        currentState.enter(this);
    }
    public GameState getCurrentState() {
        return currentState;
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
        ball.setX(WINDOW_WIDTH / 2 - ball.getWidth() / 2);
        ball.setY(paddle.getY() - ball.getHeight());
        ball.dx = 1;
        ball.dy = 1;
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

    public Ball getBall() {
        return ball;
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

    public LevelManager getLevelManager() {
        return levelManager;
    }
}