package game;

import ui.GamePanel;
import util.GameMode;

import javax.swing.*;

public class PongGame {
    public PongGame(GameMode mode) throws InterruptedException {
        JFrame frame = new JFrame("PONG - Java Edition");
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
