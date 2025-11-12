package src.ui;

import javax.swing.*;
import java.awt.*;

public class Countdown {
    private final JPanel panel;

    public Countdown(JPanel panel) {
        this.panel = panel;
    }

    public void start(Runnable onFinish) {
        new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    Graphics2D g = (Graphics2D) panel.getGraphics();
                    g.setFont(new Font("Arial", Font.BOLD, 120));
                    g.setColor(Color.WHITE);
                    g.drawString(String.valueOf(i), panel.getWidth() / 2 - 30, panel.getHeight() / 2);
                    Thread.sleep(1000);
                    panel.repaint();
                }
                onFinish.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
