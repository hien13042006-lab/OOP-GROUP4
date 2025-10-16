package org.example.demo.Objects;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import org.example.demo.GameManager;
import org.example.demo.Objects.Bricks.Brick;
import org.example.demo.Renderer;
import org.example.demo.SoundManager;

public class Ball extends MoveableObject {

    public static final int RADIUS = 11;
    public static final int SPEED = 500;
    public static final int DAMAGE_DEFAULT = 1;

    private int speed;
    private int damage;
    private boolean isWaiting = true;

    public Ball(double x, double y, double width, double height, double dx, double dy, int speed, int damage) {
        super(x, y, width, height);
        this.speed = speed;
        this.dx = dx;
        this.dy = dy;
        image = new Image(getClass().getResourceAsStream("/asset/ball.png"));
        this.damage = damage;
    }


    public boolean checkCollision(GameObject gameObject) {
        return this.x < gameObject.getX() + gameObject.getWidth()
                && this.x + this.width > gameObject.getX()
                && this.y < gameObject.getY() + gameObject.getHeight()
                && this.y + this.height > gameObject.getY();
    }

    // Va chạm với paddle
    public void bounceOffPaddle(Paddle paddle) {
        SoundManager.playSoundEffect("paddleAndBall");
        // Chỉ xử lý khi đang rơi xuống
        if (dy < 0) {
            return;
        }

        // Không va chạm thì thôi
        if (!checkCollision(paddle)) {
            return;
        }

        // Tính overlap theo từng hướng
        // Kiểm tra độ va chạm với các cạnh bên của paddle
        double overlapLeft = (this.x + this.width) - paddle.getX();
        double overlapRight = (paddle.getX() + paddle.getWidth()) - this.x;
        double overlapTop = (this.y + this.height) - paddle.getY();
        double overlapBottom = (paddle.getY() + paddle.getHeight()) - this.y;

        // Tìm overlap nhỏ nhất
        double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                Math.min(overlapTop, overlapBottom));

        // Ngưỡng để coi là "chạm góc"
        double epsilon = 0.5;

        // Kiểm tra chạm góc(chỉ góc trên bên phải or trên trái của paddle)
        boolean cornerTopLeft = Math.abs(overlapTop - overlapLeft) <= epsilon && this.dx > 0;
        boolean cornerTopRight = Math.abs(overlapTop - overlapRight) <= epsilon && this.dx < 0;

        if (cornerTopLeft || cornerTopRight) {
            // Va chạm góc và bóng đang hướng tới cạnh paddle → đảo cả dx, dy
            dx = -dx;
            dy = -dy;
            return;
        }

        // Xử lý theo cạnh có overlap nhỏ nhất
        if (minOverlap == overlapLeft) {
            // Đụng cạnh trái của paddle → bật ngược trục X, vẫn rơi xuống
            this.x = paddle.getX() - this.width;
            dx = -Math.abs(dx);
            dy = Math.abs(dy); // giữ đang rơi
            return;
        } else if (minOverlap == overlapRight) {
            // Đụng cạnh phải của paddle → bật ngược trục X, vẫn rơi xuống
            this.x = paddle.getX() + paddle.getWidth();
            dx = Math.abs(dx);
            dy = Math.abs(dy); // giữ đang rơi
            return;
        } else if (minOverlap == overlapTop) {
            // Đụng mặt trên của paddle
            // Đặt bóng ngay trên paddle để tránh kẹt
            this.y = paddle.getY() - this.height;

            // Tính tương quan vị trí chạm so với tâm paddle: [-1, 1]
            double paddleCenter = paddle.getX() + paddle.getWidth() / 2.0;
            double ballCenter = this.x + this.width / 2.0;
            double relative = (ballCenter - paddleCenter) / (paddle.getWidth() / 2.0);
            relative = clamp(relative, -1.0, 1.0);

            // Góc nảy tối đa lệch ±60° so với phương thẳng đứng
            double maxBounceAngle = Math.toRadians(60);
            double angle = relative * maxBounceAngle; // âm: lệch trái, dương: lệch phải

            // Giữ nguyên độ lớn vận tốc.
            double v = Math.sqrt(dx * dx + dy * dy);

            // Góc tính theo phương thẳng đứng: dx = v*sin(angle), dy = -v*cos(angle) (đi lên)
            dx = v * Math.sin(angle);
            dy = -Math.abs(v * Math.cos(angle)); // chắc chắn đi lên

            return;
        }
    }

    // kiểm tra cho độ chênh [min,max] = [1,1].
    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }


    public void bounceOffBrick(Brick brick) {
        SoundManager.playSoundEffect("BrickAndBall");
        // Không va chạm bỏ qua
        if (!checkCollision(brick)) {
            return;
        }

        // Kiểm tra độ va chạm với các cạnh của Brick
        // Tính overlap theo từng hướng
        double overlapLeft = (this.x + this.width) - brick.getX();
        double overlapRight = (brick.getX() + brick.getWidth()) - this.x;
        double overlapTop = (this.y + this.height) - brick.getY();
        double overlapBottom = (brick.getY() + brick.getHeight()) - this.y;

        // Tìm overlap nhỏ nhất
        double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                Math.min(overlapTop, overlapBottom));

        // Ngưỡng để coi là "chạm góc"
        double epsilon = 0.5;

        // Kiểm tra chạm góc
        boolean cornerTopLeft =
                Math.abs(overlapTop - overlapLeft) <= epsilon && this.dx > 0 && this.dy > 0;
        boolean cornerTopRight =
                Math.abs(overlapTop - overlapRight) <= epsilon && this.dx < 0 && this.dy > 0;
        boolean cornerBottomLeft =
                Math.abs(overlapBottom - overlapLeft) <= epsilon && this.dx > 0 && this.dy < 0;
        boolean cornerBottomRight =
                Math.abs(overlapBottom - overlapRight) <= epsilon && this.dx < 0 && this.dy < 0;

        if (cornerTopLeft || cornerTopRight || cornerBottomLeft || cornerBottomRight) {
            // Va chạm góc và hướng cùng chiều với brick → đảo cả dx, dy
            dx = -dx;
            dy = -dy;
            return;
        }

        // Ngược lại thì va chạm cạnh
        if (minOverlap == overlapLeft) {
            // Bên trái
            this.x = brick.getX() - this.width;
            dx = -Math.abs(dx);
        } else if (minOverlap == overlapRight) {
            // Bên phải
            this.x = brick.getX() + brick.getWidth();
            dx = Math.abs(dx);
        } else if (minOverlap == overlapTop) {
            // Phía trên
            this.y = brick.getY() - this.height;
            dy = -Math.abs(dy);
        } else if (minOverlap == overlapBottom) {
            // Phía dưới
            this.y = brick.getY() + brick.getHeight();
            dy = Math.abs(dy);
        }
    }

    // Nảy khi chạm tường
    public void bounceOffWall() {
        //chạm trái
        if (x < 0) {
            x = 0;
            dx = -dx;
        }

        // chạm phải
        else if (x > GameManager.WINDOW_WIDTH - width) {
            x = GameManager.WINDOW_WIDTH - width;
            dx = -dx;
        }

        if (y < 50) {
            y = 50;
            dy = -dy;
        } else if (y > GameManager.WINDOW_HEIGHT - height) {
            y = GameManager.WINDOW_HEIGHT - height;
            dy = -dy;
        }
    }


    @Override
    public void move(double dt) {
        System.out.println("x: " + x);
        System.out.println("y:" + y);
        x += dx * speed * dt;
        y += dy * speed * dt;
    }

    @Override
    public void update(double dt) {
        if (isWaiting == false) {
            bounceOffWall();
            move(dt);
        } else {
            return;
        }
    }


    @Override
    public void render(Renderer r) {
        r.draw(this, image);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public boolean isWaiting() {
        return isWaiting;
    }
}
