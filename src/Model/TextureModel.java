package Model;


import java.awt.image.BufferedImage;

public class TextureModel {
    public static SpriteSheetModel spritesheet;
    public static BufferedImage player, ghost;

    public TextureModel(){
        spritesheet = new SpriteSheetModel("res/sprites/spritesheet.png");

        player = spritesheet.getSprite(0, 0);
        ghost = spritesheet.getSprite(0, 16);
    }
}
