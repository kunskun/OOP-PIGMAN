package oop.pigman.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;

    public Display(String tilte, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        beginDisplay();
    }
    private void beginDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //center on screen
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); //only frame can have focus

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){ return canvas;}

    public void addKeyListener(KeyListener l){
        frame.addKeyListener(l);
    }

}
