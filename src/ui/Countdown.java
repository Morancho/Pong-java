package src.ui;

import javax.swing.*;
import java.awt.*;
import src.util.SoundPlayer;

public class Countdown {
    private final JPanel panel;

    public Countdown(JPanel panel) {
        this.panel = panel;
    }

    public void start(Runnable onFinish) {
        new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    // Fade-in
                    for (float alpha = 0f; alpha <= 1f; alpha += 0.12f) {
                        Graphics2D g = (Graphics2D) panel.getGraphics();
                        if (g != null) {
                            // fons semitransparent per fer-ho més "xulo"
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                            g.setColor(new Color(0, 0, 0));
                            g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

                            g.setFont(new Font("Consolas", Font.BOLD, 140));
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                            g.setColor(Color.WHITE);

                            String num = String.valueOf(i);
                            int textWidth = g.getFontMetrics().stringWidth(num);
                            int textHeight = g.getFontMetrics().getAscent();

                            g.drawString(num, (panel.getWidth() - textWidth) / 2, (panel.getHeight() + textHeight) / 2);
                            g.dispose();
                        }
                        Thread.sleep(40);
                    }

                    // Play beep at the moment the number reaches full opacity
                    SoundPlayer.play("beep.wav");

                    // Mantén el número una mica visible
                    Thread.sleep(500);

                    // Fade-out
                    for (float alpha = 1f; alpha >= 0f; alpha -= 0.12f) {
                        Graphics2D g = (Graphics2D) panel.getGraphics();
                        if (g != null) {
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                            g.setColor(new Color(0, 0, 0));
                            g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

                            g.setFont(new Font("Consolas", Font.BOLD, 140));
                            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                            g.setColor(Color.WHITE);

                            String num = String.valueOf(i);
                            int textWidth = g.getFontMetrics().stringWidth(num);
                            int textHeight = g.getFontMetrics().getAscent();

                            g.drawString(num, (panel.getWidth() - textWidth) / 2, (panel.getHeight() + textHeight) / 2);
                            g.dispose();
                        }
                        Thread.sleep(25);
                    }
                }

                // Un curt "GO!" opcional (també amb so)
                Graphics2D g = (Graphics2D) panel.getGraphics();
                if (g != null) {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                    g.setFont(new Font("Consolas", Font.BOLD, 80));
                    String go = "GO!";
                    int w = g.getFontMetrics().stringWidth(go);
                    int h = g.getFontMetrics().getAscent();
                    g.setColor(Color.WHITE);
                    g.drawString(go, (panel.getWidth() - w) / 2, (panel.getHeight() + h) / 2);
                    g.dispose();
                }
                SoundPlayer.play("beep.wav");
                Thread.sleep(500);

                // Neteja final abans d'iniciar
                Graphics2D g2 = (Graphics2D) panel.getGraphics();
                if (g2 != null) {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                    g2.dispose();
                }

                onFinish.run();
            } catch (InterruptedException e) {
                System.err.println("Error al compte enrere: " + e.getMessage());
            }
        }).start();
    }
}
