package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayingState implements GameState {

    @Override
    public void enter(GameManager gameManager) {
        System.out.println("Entering Playing State");
        gameManager.initializeLevel();
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
            gameManager.getPaddle().getKeyPressHandler().handle(event);
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        // Kiểm tra va chạm với gạch
        for (int i = gameManager.getBricks().size() - 1; i >= 0; i--) {
            Brick brick = gameManager.getBricks().get(i);
            if (gameManager.getBall().checkCollision(brick)) {
                gameManager.getBall().bounceOffBrick(brick);
                brick.takeHit();
                if (brick.isDestroyed()) {
                    // random xac suat ra powerup hay khong
                    double chance = gameManager.getRand().nextDouble();
                    if(chance < gameManager.getBricks().get(i).getPowerUpDropChance()) {
                        gameManager.getFallingPowerUps().add(gameManager.getBricks().get(i).makePowerUp());
                    }

                    gameManager.getBricks().remove(i);
                    gameManager.addScore(10);
                }
            }
        }

        // Kiểm tra hoàn thành level
        if (gameManager.getBricks().isEmpty()) {
            gameManager.setState(new LevelCompleteState());
            return;
        }

        // Cập nhật vật lý
        gameManager.getPaddle().update(dt);
        gameManager.getBall().update(dt);

        // Kiểm tra va chạm với paddle
        if (gameManager.getBall().checkCollision(gameManager.getPaddle())) {
            gameManager.getBall().bounceOffPaddle(gameManager.getPaddle());
        }

        // Kiểm tra mất mạng
        if (gameManager.getBall().getY() > GameManager.WINDOW_HEIGHT) {
            gameManager.loseLife();
            if (gameManager.getLives() <= 0) {
                gameManager.setState(new GameOverState());
            } else {
                gameManager.resetBallAndPaddle();
            }
        }


        //update fallingPowerUps
        for(int i = gameManager.getFallingPowerUps().size() - 1; i>=0; i--) {
            PowerUp powerUp =  gameManager.getFallingPowerUps().get(i);

            //check va chạm với paddle
            if(powerUp.checkCollision(gameManager.getPaddle())) {
                powerUp.applyEffect(gameManager.getPaddle());
                gameManager.getActivePowerUps().add(powerUp);
                gameManager.getFallingPowerUps().remove(i);
                continue;
            }

            //xóa khi rớt ra khỏi màn hình
            if(powerUp.getY() > GameManager.WINDOW_HEIGHT) {
                gameManager.getFallingPowerUps().remove(powerUp);
                continue;
            }
            //update
            powerUp.update(dt);
        }

        //update activePowerUps
        for(int i = gameManager.getActivePowerUps().size() - 1; i >=0; i--) {
            PowerUp powerUp = gameManager.getActivePowerUps().get(i);
            powerUp.update(dt);

            //xóa powerUp nếu hết thời gian
            if(powerUp.getDuration() <= 0) {
                powerUp.removeEffect(gameManager.getPaddle());
                gameManager.getActivePowerUps().remove(i);
            }
        }

    }

    @Override
    public void render(Renderer renderer, GameManager gameManager) {
        // Vẽ các đối tượng game
        renderer.draw(this, gameManager);
    }
}