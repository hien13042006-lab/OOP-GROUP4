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

    private int score;
    private int lives;
    private int currentLevel;

    private Random rand = new Random(); // random

    void startGame(Group root) {
        // Khởi tạo game state
        score = 0;
        lives = 3;
        currentLevel = 1;

        // Bắt đầu với menu state
        currentState = new MenuState();
        currentState.enter(this);

        initializeCanvas(root);
    }

    private void initializeCanvas(Group root) {
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        canvas.setFocusTraversable(true);

        // Thiết lập input handler
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentState.handleInput(event, GameManager.this);
            }
        });

        root.getChildren().add(canvas);
        canvas.requestFocus();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        renderer = new Renderer(gc);
    }

    public void initializeLevel() {
        // THONG SO PADDLE
        int paddleWidth = 100;
        int paddleHeight = 20;
        int marginBottom = 30;

        paddle = new Paddle((WINDOW_WIDTH - paddleWidth) / 2,
                WINDOW_HEIGHT - paddleHeight - marginBottom,
                paddleWidth, paddleHeight, 0, 0, 800);

        // THONG SO BALL
        int ballRadius = 10;
        ball = new Ball(WINDOW_WIDTH / 2 - ballRadius, paddle.getY() - ballRadius * 2,
                ballRadius * 2, ballRadius * 2, 1, 1, 500);

        createBricks();

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

    private void createBricks() {
        bricks = new ArrayList<>();
        int rows = 5;
        int cols = 8;
        int brickWidth = WINDOW_WIDTH / cols;
        int brickHeight = 30;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * brickWidth;
                int y = row * brickHeight + 50;

                if ((row + col) % 2 == 0) {
                    bricks.add(new NormalBrick(x, y, brickWidth, brickHeight));
                } else {
                    bricks.add(new StrongBrick(x, y, brickWidth, brickHeight));
                }
            }
        }
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
        currentLevel = 1;
    }

    public void nextLevel() {
        currentLevel++;
    }

    // Getters
    public Paddle getPaddle() { return paddle; }
    public Ball getBall() { return ball; }
    public List<Brick> getBricks() { return bricks; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getCurrentLevel() { return currentLevel; }
    public Renderer getRenderer() { return renderer; }

    public List<PowerUp> getFallingPowerUps() {
        return fallingPowerUps;
    }

    public List<PowerUp> getActivePowerUps() {
        return activePowerUps;
    }

    public Random getRand() {
        return rand;
    }
}