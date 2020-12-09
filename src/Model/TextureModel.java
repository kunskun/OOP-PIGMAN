package Model;


import Model.SpriteSheetModel;

import java.awt.image.BufferedImage;

public class TextureModel {
    public static SpriteSheetModel spritesheetAnimal, spritesheetTiger, spritesheetGround, loadScreen, losScreen, winScreen, nextScreen;
    public static BufferedImage[] playerLR, playerUD, tigerLR, tigerUD;
    public static BufferedImage groundGlass1, groundGlass2, apple, appleTree, imgLoad, imgLos, imgWin, imgNext;

    public TextureModel(){
        spritesheetAnimal = new SpriteSheetModel("res/sprites/spritesheetAnimal.png");
        spritesheetTiger = new SpriteSheetModel("res/sprites/spritesheetTiger.png");
        spritesheetGround = new SpriteSheetModel("res/sprites/spritesheetGround.png");


        loadScreen = new SpriteSheetModel("res/load/load3.jpg");
        losScreen = new SpriteSheetModel("res/load/los3.png");
        winScreen = new SpriteSheetModel("res/load/win5.jpg");
        nextScreen = new SpriteSheetModel("res/load/next.jpg");



        playerLR = new BufferedImage[2];
        playerUD = new BufferedImage[2];
        tigerLR = new BufferedImage[2];
        tigerUD = new BufferedImage[2];


        //right & left
        playerLR[0] = spritesheetAnimal.getSprite(0, 0, 16, 16);
        playerLR[1] = spritesheetAnimal.getSprite(0, 17, 16, 16);

        tigerLR[0] = spritesheetAnimal.getSprite(34, 0, 16, 16);
        tigerLR[1] = spritesheetAnimal.getSprite(34, 17, 16, 16);

        //Up & Down
        playerUD[0] = spritesheetAnimal.getSprite(17, 0, 16, 16);
        playerUD[1] = spritesheetAnimal.getSprite(17, 17, 16, 16);
        tigerUD[0] = spritesheetAnimal.getSprite(50, 0, 16, 16);
        tigerUD[1] = spritesheetAnimal.getSprite(50, 17, 16, 16);

        //ground
        groundGlass1 = spritesheetGround.getSprite(0,0, 16, 16);
        groundGlass2 = spritesheetGround.getSprite(17,0, 16, 16);
        //appleTree = spritesheetGround.getSprite(17,17, 300, 300);
        apple = spritesheetGround.getSprite(0, 17, 16, 16);

        //Screen
        imgLoad = loadScreen.getSprite(0, 0,640, 480);
        imgLos = losScreen.getSprite(0, 0, 640, 480);
        imgWin = winScreen.getSprite(0, 0, 640, 480);
        imgNext = nextScreen.getSprite(0,0,640,480);


    }
}
