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
                    for (float alpha = 0f; alpha <= 1f; alpha += 0.1f) {
                        Graphics2D g = (Graphics2D) panel.getGraphics();
                        if (g != null) {
                            g.setColor(Color.BLACK);
                            g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

                            g.setFont(new Font("Consolas", Font.BOLD, 140));
                            g.setColor(new Color(1f, 1f, 1f, alpha));

                            String num = String.valueOf(i);
                            int textWidth = g.getFontMetrics().stringWidth(num);
                            int textHeight = g.getFontMetrics().getAscent();

                            g.drawString(num, (panel.getWidth() - textWidth) / 2, (panel.getHeight() + textHeight) / 2);
                            g.dispose();
                        }
                        Thread.sleep(40); // transició suau
                    }
                    Thread.sleep(600); // pausa entre nombres
                }

                Graphics2D g = (Graphics2D) panel.getGraphics();
                if (g != null) {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                    g.dispose();
                }

                onFinish.run();
            } catch (InterruptedException e) {
                System.err.println("Error al compte enrere: " + e.getMessage());
            }
        }).start();
    }
}
