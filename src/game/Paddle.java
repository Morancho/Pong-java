package game;

import java.awt.*;

public class Paddle {
    private int x, y, width, height;
    private int speed = 8;
    private int aiSpeed = 8;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void moveUp() { if (y > 0) y -= speed; }
    public void moveDown() { if (y < 600 - height) y += speed; }

    public void moveUpAI() { if (y > 0) y -= aiSpeed; }
    public void moveDownAI() { if (y < 600 - height) y += aiSpeed; }

    public void aiMove(Ball ball) {
        if (ball.getY() + ball.getDiameter() / 2 < y + height / 2) moveUpAI();
        else if (ball.getY() + ball.getDiameter() / 2 > y + height / 2) moveDownAI();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
