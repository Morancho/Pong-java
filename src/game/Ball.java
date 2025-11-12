package game;

import java.awt.*;
import java.util.Random;

public class Ball {
    private int x, y, diameter;
    private double dx, dy, speed;
    private final double MAX_SPEED = 13;

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
        speed = 8; // més ràpida
        dx = rand.nextBoolean() ? speed : -speed;
        dy = (rand.nextDouble() - 0.5) * speed;
    }

    public void move() {
        x += dx;
        y += dy;
        if (y <= 0 || y >= 600 - diameter) dy = -dy;
    }

    public void bounceWithAngle(Paddle paddle) {
        // Calcular on toca dins de la pala (-1 = part superior, +1 = inferior)
        double intersectY = (y + diameter / 2.0) - (paddle.getY() + paddle.getBounds().height / 2.0);
        double normalized = intersectY / (paddle.getBounds().height / 2.0);
        double angle = normalized * Math.toRadians(45);

        // Direcció segons quin costat és la pala
        double direction = (x < 400) ? 1 : -1;

        speed = Math.min(speed * 1.07, MAX_SPEED);
        dx = direction * speed * Math.cos(angle);
        dy = speed * Math.sin(angle);
    }

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
