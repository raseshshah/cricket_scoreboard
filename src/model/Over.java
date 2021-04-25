package model;

import java.beans.Statement;
import java.util.concurrent.CopyOnWriteArrayList;

public class Over {
    public static final int NO_BALLS = 6;
    private final String bowlerId;
    private final CopyOnWriteArrayList<Ball> balls = new CopyOnWriteArrayList<>();
    volatile private int remaingBalls = NO_BALLS;

    public Over(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public void addBall(Ball ball) {
        if (ball.getType() == Ball.Type.VALID) remaingBalls--;
        balls.add(ball);
    }

    public String getBolwerId() {
        return bowlerId;
    }

    public boolean isCompleted() {
        return remaingBalls == 0;
    }

    public boolean isStarted() {
        return remaingBalls == 6;
    }

    public int getRemaingBalls() {
        return remaingBalls;
    }

    public boolean isMaiden() {
        return balls.stream().filter(Ball::isDot).count() == NO_BALLS;
    }
}
