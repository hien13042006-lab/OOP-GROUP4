package org.example.demo;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Random;

public class PlayingState implements GameState {
    private Renderer renderer ;
    private Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<PowerUp> activePowerUps; //các powerUp đang áp dụng
    private List<PowerUp> fallingPowerUps; //các powerUp đang rơi
    private Random rand;

    @Override
    public void enter(GameManager gameManager) {
        System.out.println("Entering Playing State");

        gameManager.initializeLevel();
        renderer = gameManager.getRenderer();
        paddle = gameManager.getPaddle();
        balls = gameManager.getBalls();
        bricks = gameManager.getBricks();
        activePowerUps = gameManager.getActivePowerUps();
        fallingPowerUps = gameManager.getFallingPowerUps();
        rand = gameManager.getRand();

    }

    @Override
    public void exit(GameManager gameManager) {
        System.out.println("Exiting Playing State");
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ESCAPE) {
            gameManager.setState(new PausedState());
        } else {
            // Chuyển input cho paddle
            paddle.getKeyPressHandler().handle(event);
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Kiểm tra va chạm với gạch
        for (int i = bricks.size() - 1; i >= 0; i--) {
            Brick brick = bricks.get(i);
            for(Ball ball : balls)
            {
                if (ball.checkCollision(brick)) {
                    ball.bounceOffBrick(brick);
                    brick.takeHit();
                    if (brick.isDestroyed()) {
                        // random xac suat ra powerup hay khong
                        double chance = rand.nextDouble();
                        if(chance < bricks.get(i).getPowerUpDropChance()) {
                            fallingPowerUps.add(bricks.get(i).makePowerUp());
                        }

                        bricks.remove(i);
                        gameManager.addScore(10);
                    }
                }
            }

        }

        // Kiểm tra hoàn thành level
        if (bricks.isEmpty()) {
            if (gameManager.getLevelManager().hasNextLevel()) {
                gameManager.setState(new LevelCompleteState());
            } else {
                // ⭐ NẾU KHÔNG CÒN LEVEL NÀO → GAME COMPLETE
                gameManager.setState(new GameCompleteState());
            }
            return;
        }

        // Cập nhật vật lý
        paddle.update(dt);
        for(int i = balls.size() - 1; i >= 0; i--) {
            balls.get(i).update(dt);
            // Kiểm tra va chạm với paddle
            if (balls.get(i).checkCollision(paddle)) {
                balls.get(i).bounceOffPaddle(paddle);
            }

            // kiểm tra rớt khỏi màn hình
            if(balls.get(i).getY() > gameManager.WINDOW_HEIGHT) {
                balls.remove(i);
            }
        }

        // Kiểm tra mất mạng
        if (balls.isEmpty()) {
            gameManager.loseLife();
            if (gameManager.getLives() <= 0) {
                gameManager.setState(new GameOverState());
            } else {
                gameManager.resetBallAndPaddle();
            }
        }


        //update fallingPowerUps
        for(int i = fallingPowerUps.size() - 1; i>=0; i--) {
            PowerUp powerUp =  fallingPowerUps.get(i);

            //check va chạm với paddle
            if(powerUp.checkCollision(paddle)) {
                if(activePowerUps.contains(powerUp)) {
                    powerUp.removeEffect(paddle, balls);
                    activePowerUps.remove(powerUp);
                }
                powerUp.applyEffect(paddle, balls);
                activePowerUps.add(powerUp);
                fallingPowerUps.remove(i);
                continue;
            }

            //xóa khi rớt ra khỏi màn hình
            if(powerUp.getY() > GameManager.WINDOW_HEIGHT) {
                fallingPowerUps.remove(powerUp);
                continue;
            }
            //update
            powerUp.update(dt);
        }

        System.out.println("activePowerUps: " + activePowerUps.size());
        //update activePowerUps
        for(int i = activePowerUps.size() - 1; i >=0; i--) {
            PowerUp powerUp = activePowerUps.get(i);
            powerUp.update(dt);

            //xóa powerUp nếu hết thời gian
            if(powerUp.getDuration() <= 0) {
                powerUp.removeEffect(paddle, balls);
                activePowerUps.remove(i);
            }
        }

    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        // Vẽ các đối tượng game
        renderer.draw(this, gameManager);
    }
}