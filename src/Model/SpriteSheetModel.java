package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheetModel {
    private BufferedImage sheet;
    public SpriteSheetModel(String path){
        try{
            sheet = ImageIO.read(new File(path));
        } catch (IOException e){
            System.out.println("failed to load image!");
        }
    }

    public BufferedImage getSprite(int xx, int yy, int w, int h){
        return sheet.getSubimage(xx, yy, 16, 16);
    }
}
