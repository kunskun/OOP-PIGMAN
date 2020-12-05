package com.company;

import java.awt.*;

public class Player extends Rectangle {

    private static final long serialVersionUID = 1L;

    public boolean right, left, up, down;
    private int speed = 4;

    public Player(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void tick(){
        if(right) {x += speed;}
        else if(left) {x -= speed;}
        else if(up) {y -= speed;}
        else if(down) {y += speed;}
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
    }


}
