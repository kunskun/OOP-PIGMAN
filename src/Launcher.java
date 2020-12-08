import Controller.GameController;
import Model.GetScore;


import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String[] args){
        // write your code here
        GameController game = new GameController();
        JFrame f = new JFrame();
        f.setTitle(GameController.TITLE);
        f.setLayout(new BorderLayout());
        f.add(game, BorderLayout.CENTER);
        f.setResizable(false);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        //test score
//        JPanel p = new JPanel();
//        JLabel la = new JLabel("14:05:12:09");
//        p.add(la);
//        p.setSize(640, 20);
//        f.add(p, BorderLayout.NORTH);
//        la.setHorizontalTextPosition(JLabel.RIGHT);

        f.setVisible(true);
        GetScore s = new GetScore();
        s.getScore();

        game.start();
    }
}
