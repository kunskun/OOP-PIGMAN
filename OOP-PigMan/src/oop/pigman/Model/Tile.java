package oop.pigman.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILESIZE = 30;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
    }

    public boolean isSolid(){
        return false;
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILESIZE, TILESIZE, null);
    }

    public int getID(){
        return id;
    }
}
