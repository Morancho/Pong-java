package main;

import game.PongGame;
import util.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().showMenu());
    }

    private void showMenu() {
        JFrame frame = new JFrame("PONG - 1st Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1));

        JButton localButton = new JButton("Mode 2 jugadors");
        JButton aiButton = new JButton("Mode contra l'ordinador");

        localButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new PongGame(GameMode.LOCAL);
        });

        aiButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            new PongGame(GameMode.VS_AI);
        });

        frame.add(new JLabel("Tria el mode de joc:", SwingConstants.CENTER));
        frame.add(localButton);
        frame.add(aiButton);

        frame.setVisible(true);
    }

}
