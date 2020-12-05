package com.company;

import java.awt.*;

public class Apple extends Rectangle {

    private static final long serialVersionUID = 1L;

    public Apple(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x, y, 32, 32);
    }

}
