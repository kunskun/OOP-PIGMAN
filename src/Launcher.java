import Controller.GameController;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String[] args){
        // write your code here
        GameController game = new GameController();
        JFrame f = new JFrame();
        f.setTitle(GameController.TITLE);
        f.add(game);
        f.setResizable(false);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        game.start();

    }
}
