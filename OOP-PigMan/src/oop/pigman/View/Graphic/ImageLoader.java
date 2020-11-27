package oop.pigman.View.Graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(ImageLoader.class.getResource(path));
        } catch(IOException e){
            System.out.println("No Image");
            e.printStackTrace();
            System.exit(1);
        }
        return image;
    }
}
