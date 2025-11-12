package src.game;

import java.awt.*;
import java.util.Random;

public class Ball {
    private int x, y, diameter;
    private double dx, dy, speed;
    private final double MAX_SPEED = 10;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        reset();
    }

    public void reset() {
        x = 400 - diameter / 2;
        y = 300 - diameter / 2;
        Random rand = new Random();
        speed = 5;
        dx = rand.nextBoolean() ? speed : -speed;
        dy = (rand.nextDouble() - 0.5) * speed;
    }

    public void move() {
        x += dx;
        y += dy;

        if (y <= 0 || y >= 600 - diameter) dy = -dy;
    }

    public void increaseSpeed() {
        if (Math.abs(dx) < MAX_SPEED) {
            dx *= 1.05;
            dy *= 1.05;
        }
    }

    public void bounceX() { dx = -dx; }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getDiameter() { return diameter; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }
}
