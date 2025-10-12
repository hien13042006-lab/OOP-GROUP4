package org.example.demo;

import org.example.demo.Objects.Ball;
import org.example.demo.Objects.Paddle;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Objects.Bricks.NormalBrick;
import org.example.demo.GameManager;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    @Test
    void  testCheckCollision() {
        //Test va chạm
        Ball ball = new Ball(100, 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        Paddle paddle = new Paddle(102, 102, Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT, 1, 1, Paddle.PADDLE_SPEED);
        assertTrue(ball.checkCollision(paddle));
    }

    @Test
    void testMove() {
        //Test di chuyển
        Ball ball1 = new Ball(100, 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        Ball ball2 = new Ball(100, 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        ball1.move(10);
        assertNotEquals(ball1.getX(), ball2.getX());
        assertNotEquals(ball1.getY(), ball2.getY());
    }

    @Test
    void testbounceOffWall_leftSide() {
        //Test chạm trái (đặt x = 0, đổi chiều dx)
        Ball ballLeft = new Ball(-5, 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        ballLeft.bounceOffWall();
        assertEquals(0, ballLeft.getX());
        assertEquals(-1, ballLeft.getDx());
    }

    @Test
    void testbounceOffWall_rightSide() {
        //Test chạm phải (đặt x = vtr bên phải WINDOW - Width, đổi chiều dx)
        Ball ballRight = new Ball(GameManager.WINDOW_WIDTH, 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        ballRight.bounceOffWall();

        assertEquals(GameManager.WINDOW_WIDTH - ballRight.getWidth(), ballRight.getX());
        assertEquals(-1, ballRight.getDx());
    }

    @Test
    void testbounceOffWall_topSide() {
        //Test chaạm trần( đặt y = 0, đổi chiều dy)
        Ball ballTop = new Ball(100, -1, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        ballTop.bounceOffWall();

        assertEquals(0, ballTop.getY());
        assertEquals(-1, ballTop.getDy());
    }

    @Test
    void testbounceOffWall_middleSide() {
        //Test bóng khi ko chạm gì(không làm gì)
        Ball ballMiddle = new Ball( 100 , 100, 2 * Ball.RADIUS, 2 * Ball.RADIUS, 1, 1, Ball.SPEED);
        ballMiddle.bounceOffWall();

        assertEquals (100, ballMiddle.getX());
        assertEquals (100, ballMiddle.getY());
        assertEquals (1, ballMiddle.getDx());
        assertEquals (1, ballMiddle.getDy());
    }

    @Test
    void bounceOffBrick_topSide() {
        //Cho vị trí bóng hơi lấn ở trên gạch (minOverlap = overlapTop)
        //Vị trí bóng trên gạch và đi lên (dy = -abs(dy))
        NormalBrick brick = new NormalBrick(200, 100, 200, 100);
        Ball ball = new Ball(210, 90, 20, 20, 1, 1, Ball.SPEED);
        ball.bounceOffBrick(brick);

        assertEquals(brick.getY() - ball.getHeight(), ball.getY());
        assertEquals(-1, ball.getDy());
    }

    @Test
    void bounceOffBrick_botSide() {
        //Cho vị trí bóng hơi lấn ở dưới gạch (minOverlap = overlapBot)
        //Vị trí bóng dưới gạch và đi xuống (dy = abs(dy))
        NormalBrick brick = new NormalBrick(200, 100, 200, 100);
        Ball ball = new Ball(210, 190, 20, 20, 1, -1, Ball.SPEED);
        ball.bounceOffBrick(brick);

        assertEquals(brick.getY() + brick.getHeight(), ball.getY());
        assertEquals(1, ball.getDy());
    }

    @Test
    void bounceOffBrick_leftSide() {
        //Cho vị trí bóng hơi lấn ở trên trái (minOverlap = overlapLeft)
        //Vị trí bóng trái gạch và sang trái (dx = -abs(dx))
        NormalBrick brick = new NormalBrick(200, 100, 200, 100);
        Ball ball = new Ball(200, 150, 20, 20, 1, 1, Ball.SPEED);
        ball.bounceOffBrick(brick);

        assertEquals(brick.getX() - ball.getWidth(), ball.getX());
        assertEquals(-1, ball.getDx());
    }

    @Test
    void bounceOffBrick_RightSide() {
        //Cho vị trí bóng hơi lấn ở trên phải (minOverlap = overlapRight)
        //Vị trí bóng phải gạch và sang phải (dx = abs(dx))
        NormalBrick brick = new NormalBrick(200, 100, 200, 100);
        Ball ball = new Ball(400, 150, 20, 20, -1, 1, Ball.SPEED);
        ball.bounceOffBrick(brick);

        assertEquals(brick.getX() + brick.getWidth(), ball.getX());
        assertEquals(1, ball.getDx());
    }

    @Test
    void bounceOffBrick_cornerTopLeft() {
        //Đặt để bóng va chạm với cạnh trên bên trái của gạch (abs(overlapTop - overlapLeft) min < epsilon = 0.5)
        //Cho overlapTop - overlapLeft = 0
        //Khi đó đổi chiều dx, dy
        NormalBrick brick = new NormalBrick(200, 100, 200, 100);
        Ball ball = new Ball(190, 90, 20, 20, -1, 1, Ball.SPEED);
        ball.bounceOffBrick(brick);

        assertEquals(1, ball.getDx());
        assertEquals(-1, ball.getDy());
    }

    @Test
    void bounceOffPaddle_leftSide() {
        //Đặt bóng hơi lấn trái paddle
        //Khi đó đặt bóng ở bên trái paddle, bóng sẽ tiếp tục di chuyển xuống dươi và đổi chiều dx
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(190, 800, 20, 20, 1, 1, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        assertEquals(paddle.getX() - ball.getWidth(), ball.getX());
        assertEquals(-1, ball.getDx());
        assertEquals(1, ball.getDy());
    }

    @Test
    void bounceOffPaddle_RightSide() {
        //Đặt bóng hơi lấn phải paddle
        //Khi đó đặt bóng ở bên phải paddle, bóng sẽ tiếp tục di chuyển xuống dươi và đổi chiều dx
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(290, 800, 20, 20, -1, 1, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        assertEquals(paddle.getX() + paddle.getWidth(), ball.getX());
        assertEquals(1, ball.getDx());
        assertEquals(1, ball.getDy());
    }

    @Test
    void bounceOffPaddle_cornerTopLeft() {
        //Đặt để bóng va chạm với cạnh trên bên trái của gạch (abs(overlapTop - overlapLeft) min < epsilon = 0.5)
        //Cho overlapTop - overlapLeft = 0
        //Khi đó đổi chiều dx, dy
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(190, 790, 20, 20, 1, 1, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        assertEquals(-1, ball.getDx());
        assertEquals(-1, ball.getDy());
    }

    @Test
    void bounceOffPaddle_cornerTopRight() {
        //Đặt để bóng va chạm với cạnh trên bên trái của gạch (abs(overlapTop - overlapRight) min < epsilon = 0.5)
        //Cho overlapTop - overlapLeft = 0
        //Khi đó đổi chiều dx, dy
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(290, 790, 20, 20, 1, 1, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        assertEquals(-1, ball.getDx());
        assertEquals(-1, ball.getDy());
    }

    @Test
    void bounceOffPaddle_topLeftAngle() {
        //Đặt bóng lấn dịch sang bên trái trung tâm Paddle
        //Khi đó độ va chạm là ((220 + 20/2) - (200 + 100/2)) / (100/2) = -2/5
        //Angle = -2/5 * pi / 3 = - 2 * pi /5 (do góc tối đa là 60 độ hay pi /3)
        //Lúc đó v = sqrt(3^2 + 4^2) = 5
        //Sau va chạm dx = v * sin(angle) = -2.033683
        //            dy = -abs(v * cos(angle)) = -4.567727
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(220, 790, 20, 20, 3, 4, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        //Đặt bóng ngay trên paddle
        assertEquals(220, ball.getX());
        assertEquals(780, ball.getY());

        //Kiểm tra dx,dy sau khi va chạm
        assertEquals(-2.033683, ball.getDx(), 1e-6);
        assertEquals(-4.567727, ball.getDy(), 1e-6);
    }

    @Test
    void bounceOffPaddle_topRightAngle() {
        //Đặt bóng lấn dịch sang bên trái trung tâm Paddle
        //Khi đó độ va chạm là ((270 + 20/2) - (200 + 100/2)) / (100/2) = 3/5
        //Angle = 3/5 * pi / 3 = pi /5 (do góc tối đa là 60 độ hay pi /3)
        //Lúc đó v = sqrt(3^2 + 4^2) = 5
        //Sau va chạm dx = v * sin(angle) = -2,938926
        //            dy = -abs(v * cos(angle)) = -4,045084
        Paddle paddle = new Paddle(200, 800, 100, 40, 10, 10, Paddle.PADDLE_SPEED);
        Ball ball = new Ball(270, 790, 20, 20, 3, 4, Ball.SPEED);
        ball.bounceOffPaddle(paddle);

        //Đặt bóng ngay trên paddle
        assertEquals(270, ball.getX());
        assertEquals(780, ball.getY());

        //Kiểm tra dx,dy sau khi va chạm
        assertEquals(2.938926, ball.getDx(), 1e-6);
        assertEquals(-4.045084, ball.getDy(), 1e-6);
    }
}
