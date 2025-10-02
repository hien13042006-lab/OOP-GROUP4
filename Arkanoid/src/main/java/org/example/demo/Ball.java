package org.example.demo;

import java.net.PasswordAuthentication;

public class Ball extends MoveableObject {
    private int speed;

    public Ball(double x, double y, double width, double height, double dx, double dy, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        this.dx = dx;
        this.dy = dy;
    }


    boolean checkCollision(GameObject gameObject) {
        return this.x < gameObject.getX() + gameObject.getWidth() &&
                this.x + this.width > gameObject.getX() &&
                this.y < gameObject.getY() + gameObject.getHeight() &&
                this.y + this.height > gameObject.getY();
    }


    void bounceOffPaddle(Paddle paddle) {
        // Đặt bóng ngay trên paddle để tránh kẹt
        y = paddle.getY() - this.height;

        // Đảo chiều dọc
        dy = -dy;

        // Tính toán vị trí va chạm (0.0 trái → 1.0 phải)
        double hitPos = (x - paddle.getX() + width / 2.0) / paddle.getWidth();
        dx = (hitPos - 0.5) * 8; // càng xa giữa thì dx càng lớn

        // Chuẩn hóa tốc độ để bóng không chậm/nhanh bất thường
        double speed = Math.sqrt(dx * dx + dy * dy);
        double desiredSpeed = 5;
        dx = dx / speed * desiredSpeed;
        dy = dy / speed * desiredSpeed;
    }


    void bounceOffBrick(Brick brick) {
        // Đặt bóng ngay trên paddle để tránh kẹt
        y = brick.getY() + this.height;

        // Đảo chiều dọc
        dy = -dy;

        // Tính toán vị trí va chạm (0.0 trái → 1.0 phải)
        double hitPos = (x - brick.getX() + width / 2.0) / brick.getWidth();
        dx = (hitPos - 0.5) * 8; // càng xa giữa thì dx càng lớn

        // Chuẩn hóa tốc độ để bóng không chậm/nhanh bất thường
        double speed = Math.sqrt(dx * dx + dy * dy);
        double desiredSpeed = 5;
        dx = dx / speed * desiredSpeed;
        dy = dy / speed * desiredSpeed;
    }


    void bounceOffWall() {
        if (x < 0) {
            x = 0;
            dx = -dx;
        } else if (x > 800 - width) {
            x = 800 - width;
            dx = -dx;
        }

        if (y < 0) {
            y = 0;
            dy = -dy;
        } else if (y > 600 - height) {
            y = 600 - height;
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
        bounceOffWall();
        move(dt);
    }


    @Override
    public void render(Renderer r) {
        r.draw(this);
    }
}
