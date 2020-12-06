package Model;


import java.awt.image.BufferedImage;

public class TextureModel {
    public static SpriteSheetModel spritesheet, spritesheetTiger, spritesheetGround;
    public static BufferedImage[] playerLR, playerUD;
    public static BufferedImage ghost, groundGlass1, groundGlass2;

    public TextureModel(){
        spritesheet = new SpriteSheetModel("res/sprites/spritesheet.png");
        spritesheetTiger = new SpriteSheetModel("res/sprites/spritesheetTiger.png");
        spritesheetGround = new SpriteSheetModel("res/sprites/spritesheetGround.png");

        playerLR = new BufferedImage[2];
        playerUD = new BufferedImage[2];

        //right & left
        playerLR[0] = spritesheet.getSprite(18, 16);
        playerLR[1] = spritesheet.getSprite(18, 0);

        //Up & Down
        playerUD[0] = spritesheet.getSprite(35, 18);
        playerUD[1] = spritesheet.getSprite(36, 0);
        ghost = spritesheetTiger.getSprite(0, 0);

        //ground
        groundGlass1 = spritesheetGround.getSprite(0,0);
        groundGlass2 = spritesheetGround.getSprite(17,0);
    }
}
