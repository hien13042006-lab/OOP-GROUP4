package org.example.demo;

public class PowerUp extends GameObject{
    protected double duration;
    protected boolean isFalling;
    protected boolean isActive;
    protected double dx = 0;
    protected double dy = 1;
    protected int speed;

    public PowerUp() {

    }

    public void applyEffect(Paddle paddle){

    }

    public void removeEffect(Paddle paddle){

    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public void update(double dt) {
        if(isFalling){
            y+=this.speed*dt;
        }

        if(isActive){
            setDuration(this.getDuration() - dt);
        }
    }

    @Override
    public void render(Renderer r) {
        r.draw(this);
    }
}
