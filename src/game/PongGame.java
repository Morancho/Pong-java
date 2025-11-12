package src.game;

import src.ui.GamePanel;
import src.util.GameMode;

import javax.swing.*;

public class PongGame {
    public PongGame(GameMode mode) {
        JFrame frame = new JFrame("PONG - Oriol Edition");
        GamePanel panel = new GamePanel(mode);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.startGame();
    }
}
