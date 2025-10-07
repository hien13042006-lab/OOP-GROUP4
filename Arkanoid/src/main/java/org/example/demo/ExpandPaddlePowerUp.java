package org.example.demo;

public class ExpandPaddlePowerUp extends PowerUp {
    //tỉ lệ tăng kích cỡ.
    public static final double EXPAND_AMOUNT = 20;

    public static final double EXPAND_DURATION = 7.0;
    public static final int EXPAND_SPEED = 200;

    //khởi tạo duration.
    public ExpandPaddlePowerUp(double x, double y, double width, double height) {
        super();
        duration = EXPAND_DURATION;
        speed =  EXPAND_SPEED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isFalling = true;
        isActive = false;
    }

    @Override
    public void applyEffect(Paddle paddle){
        isFalling = false;
        isActive = true;
        //độ tăng chiều rộng paddle.
        double paddleIncrement = EXPAND_AMOUNT;

        //giảm tọa độ theo x đi 1/2 mức tăng.
        paddle.setX(paddle.getX()-paddleIncrement/2);
        //tăng chiều rộng.
        paddle.setWidth(paddle.getWidth()+paddleIncrement);
    }

    @Override
    public void removeEffect(Paddle paddle){
        isActive = false;
        //độ tăng chiều rộng paddle.
        double paddleDecrement = EXPAND_AMOUNT;

        //giảm tọa độ theo x đi 1/2 mức tăng.
        paddle.setX(paddle.getX()+paddleDecrement/2);
        //tăng chiều rộng.
        paddle.setWidth(paddle.getWidth()-paddleDecrement);
    }
}
