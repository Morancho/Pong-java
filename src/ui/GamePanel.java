package src.ui;

import src.game.*;
import src.util.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800, HEIGHT = 600;
    private Paddle left, right;
    private Ball ball;
    private Score score;
    private GameMode mode;
    private Timer timer;
    private boolean wPressed, sPressed, upPressed, downPressed;

    public GamePanel(GameMode mode) {
        this.mode = mode;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        left = new Paddle(50, HEIGHT / 2 - 50, 15, 100);
        right = new Paddle(WIDTH - 65, HEIGHT / 2 - 50, 15, 100);
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 20);
        score = new Score();
    }

    public void startGame() {
        new Countdown(this).start(() -> {
            timer = new Timer(16, this);
            timer.start();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wPressed) left.moveUp();
        if (sPressed) left.moveDown();

        if (mode == GameMode.LOCAL) {
            if (upPressed) right.moveUp();
            if (downPressed) right.moveDown();
        } else {
            right.aiMove(ball);
        }

        ball.move();

        // Col·lisions amb pales
        if (ball.getBounds().intersects(left.getBounds()) || ball.getBounds().intersects(right.getBounds())) {
            ball.bounceX();
            ball.increaseSpeed();
        }

        // Punts
        if (ball.getX() < 0) {
            score.rightPoint();
            ball.reset();
        } else if (ball.getX() > WIDTH) {
            score.leftPoint();
            ball.reset();
        }

        if (score.hasWinner()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, score.getWinner() + " ha guanyat!");
            System.exit(0);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Consolas", Font.BOLD, 36));
        g2.setColor(Color.WHITE);
        g2.drawString(score.getLeft() + "   " + score.getRight(), WIDTH / 2 - 50, 50);

        ball.draw(g2);
        left.draw(g2);
        right.draw(g2);
    }

    // Controls
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> wPressed = true;
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> wPressed = false;
            case KeyEvent.VK_S -> sPressed = false;
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {}
}
