package oop.pigman.View.Graphic;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage s;

    public SpriteSheet(BufferedImage s){
        this.s = s;
    }

    public BufferedImage getFullImage(){
        return s;
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return s.getSubimage(x, y, width, height);
    }
}
