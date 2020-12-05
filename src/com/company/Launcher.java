package com.company;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        // write your code here
        Game game = new Game();
        JFrame f = new JFrame();
        f.setTitle(Game.TITLE);
        f.add(game);
        f.setResizable(false);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        f.setVisible(true);

        game.start();

        new RunFrame();
    }
}
