package com.company;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetScreen {
    private BufferedImage image;
    public SetScreen(String path){
        try{
            image = ImageIO.read(new File(path));
        } catch (IOException e){
            System.out.println("failed to load image!");
        }
    }

    public BufferedImage getSprite(int xx, int yy){
        return image.getSubimage(xx, yy, 640, 480);
    }

}
