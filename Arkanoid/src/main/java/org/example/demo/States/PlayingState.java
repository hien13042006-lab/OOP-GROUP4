package org.example.demo.States;

import java.util.List;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.demo.GameManager;
import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.UnbreakableBrick;
import org.example.demo.Objects.GameObject;
import org.example.demo.Objects.Paddle;
import org.example.demo.Objects.PowerUps.PowerUp;
import org.example.demo.Renderer;

public class PlayingState implements GameState {

    private Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<PowerUp> activePowerUps; //các powerUp đang áp dụng
    private List<PowerUp> fallingPowerUps; //các powerUp đang rơi
    private Random rand;

    @Override
    public boolean enter(GameManager gameManager) {
        System.out.println("Entering Playing State");

        //GameObject.soundManager.stopSoundEffect("BackgroundSoundtrack");
        gameManager.initializeLevel();
        paddle = gameManager.getPaddle();
        balls = gameManager.getBalls();
        bricks = gameManager.getBricks();
        activePowerUps = gameManager.getActivePowerUps();
        fallingPowerUps = gameManager.getFallingPowerUps();
        rand = gameManager.getRand();
        return true;

    }

    @Override
    public boolean exit(GameManager gameManager) {
        System.out.println("Exiting Playing State");
        return true;
    }

    @Override
    public void handleInput(KeyEvent event, GameManager gameManager) {
        if (event.getCode() == KeyCode.ESCAPE) {
            gameManager.getGameStateMachine().pushState(gameManager, new PausedState());
        } else if (event.getCode() == KeyCode.SPACE) {
            for (Ball ball : balls) {
                ball.setWaiting(false);
            }
        } else {// Chuyển input cho paddle
            paddle.getKeyPressHandler().handle(event);
        }
    }

    @Override
    public void update(double dt, GameManager gameManager) {
        //System.out.println("GameStateMachine size: " + gameManager.getGameStateMachine().getGameStateList().size());
        //System.out.println("LevelManager size: " + gameManager.getLevelManager().getLevels().size());
        // Kiểm tra va chạm với gạch
        for (int i = bricks.size() - 1; i >= 0; i--) {
            bricks.get(i).update(dt);
        }
        for (int i = bricks.size() - 1; i >= 0; i--) {
            Brick brick = bricks.get(i);
            for (Ball ball : balls) {
                if (ball.checkCollision(brick)) {
                    //brick nhận damage
                    brick.takeHit(ball.getDamage());
                    //nếu mà ball mạnh quá làm máu brick tụt sâu thì ball không nảy mà đi xuyên
                    if (brick.getHitPoints() >= -100) {
                        ball.bounceOffBrick(brick);
                    }
                    //kiểm tra brick bị phá hủy
                    if (brick.isDestroyed()) {
                        // random xac suat ra powerup hay khong
                        int chance = rand.nextInt(100);
                        if (chance < bricks.get(i).getPowerUpDropChance()) {
                            fallingPowerUps.add(bricks.get(i).makePowerUp());
                        }

                        bricks.remove(i);
                        gameManager.addScore(10);
                    }
                }
            }

        }

        // Kiểm tra hoàn thành level
        int countUnbreakableBrick = 0;
        for (Brick brick : bricks) {
            if (brick instanceof UnbreakableBrick) {
                countUnbreakableBrick++;
            }
        }
        if (countUnbreakableBrick == bricks.size()) {
            if (gameManager.getLevelManager().hasNextLevel()) {
                gameManager.getGameStateMachine()
                        .changeState(gameManager, new LevelCompleteState());
            } else {
                // ⭐ NẾU KHÔNG CÒN LEVEL NÀO → GAME COMPLETE
                gameManager.getGameStateMachine().changeState(gameManager, new GameCompleteState());
            }
            return;
        }

        // Cập nhật vật lý
        paddle.update(dt);
        for (int i = balls.size() - 1; i >= 0; i--) {
            if (balls.get(i).isWaiting()) {
                balls.get(i).setX(paddle.getX() + paddle.getWidth() / 2 - Ball.RADIUS);
                balls.get(i).setY(paddle.getY() - Ball.RADIUS * 2);
            } else {
                balls.get(i).update(dt);
                // Kiểm tra va chạm với paddle
                if (balls.get(i).checkCollision(paddle)) {
                    balls.get(i).bounceOffPaddle(paddle);
                }

                // kiểm tra rớt khỏi màn hình
                if (balls.get(i).getY() > gameManager.WINDOW_HEIGHT) {
                    balls.remove(i);
                }
            }
        }
        // Kiểm tra mất mạng
        if (balls.isEmpty()) {
            gameManager.loseLife();
            if (gameManager.getLives() <= 0) {
                gameManager.getGameStateMachine().changeState(gameManager, new GameOverState());
            } else {
                gameManager.resetBallAndPaddle();
            }
        }
        System.out.println("speed: " + balls.getFirst().getSpeed());

        //update fallingPowerUps
        for (int i = fallingPowerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = fallingPowerUps.get(i);

            //check va chạm với paddle
            if (powerUp.checkCollision(paddle)) {
                if (activePowerUps.contains(powerUp)) {
                    powerUp.removeEffect(paddle, balls);
                    activePowerUps.remove(powerUp);
                }
                powerUp.applyEffect(paddle, balls);
                activePowerUps.add(powerUp);
                fallingPowerUps.remove(i);
                continue;
            }

            //xóa khi rớt ra khỏi màn hình
            if (powerUp.getY() > GameManager.WINDOW_HEIGHT) {
                fallingPowerUps.remove(i);
                continue;
            }
            //update
            powerUp.update(dt);
        }

        //System.out.println("activePowerUps: " + activePowerUps.size());
        //update activePowerUps
        for (int i = activePowerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = activePowerUps.get(i);
            powerUp.update(dt);

            //xóa powerUp nếu hết thời gian
            if (powerUp.getDuration() <= 0) {
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