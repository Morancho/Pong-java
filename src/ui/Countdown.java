package pong.ui;

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
                    if (g != null) {
                        // Esborra pantalla abans de cada número
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

                        g.setFont(new Font("Arial", Font.BOLD, 120));
                        g.setColor(Color.WHITE);

                        String num = String.valueOf(i);
                        int textWidth = g.getFontMetrics().stringWidth(num);
                        int textHeight = g.getFontMetrics().getAscent();

                        g.drawString(num, (panel.getWidth() - textWidth) / 2, (panel.getHeight() + textHeight) / 2);
                        g.dispose();
                    }
                    Thread.sleep(1000);
                }

                // Neteja pantalla abans de començar
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
