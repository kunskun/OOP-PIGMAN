package Controller;

import Model.SaveScore;
import Model.TextureModel;
import Model.soundModel;

import java.awt.*;

public class PlayerController extends Rectangle {
    private static final long serialVersionUID = 1L;
    public int point=0;
    public static GameController g;

    public boolean right, left, up, down;
    private int speed = 4;
    //set time aniamtion
    private int time, targetTime = 10;
    public  int imageIndex = 0;

    //
    private int lastDir = 1;

    public PlayerController(int x, int y){
        if (g.begin) {
            g.begin = false;
            g.r1.start();
        } else {
            g.r1.resume();
        }

        //r1.resume();
        g.soundBG.start();
        setBounds(x, y, 32, 32);
    }

    public void tick(){
        if(right && canMove(x+speed, y)) {
            x += speed;
            lastDir = 1;
        }
        if(left && canMove(x-speed, y)) {
            x -= speed;
            lastDir = -1;
        }
        if(up && canMove(x, y-speed)) {
            y -= speed;
            lastDir = 2;

        }
        if(down && canMove(x, y+speed)) {
            y += speed;
            lastDir = -2;
        }

        LevelController level = GameController.level;

        for(int i=0; i < level.apples.size(); i++){
            if(this.intersects(level.apples.get(i))){
                level.apples.remove(i);
                point++;
                break;
            }
        }

        if(level.apples.size() == 0){
            //Game end when you eat all apple is
            // count is use for change level
//            new SaveScore(tm.getLastTime());
            if (GameController.count == 0) {
                g.soundBG.stop();
                new soundModel("res/sound/win.wav", false).start();
                GameController.count++;
                System.out.println(GameController.count);
                GameController.STATE = GameController.LEVEL_PASSED;
//                new SaveScore(tm.getLastTime());
                return;
            }
            else{
                g.soundBG.stop();
                g.yourTime = g.tm.getLastTime();
                new soundModel("res/sound/win.wav", false).start();
                GameController.STATE = GameController.WIN_SCREEN;

//                new SaveScore(tm.getLastTime());
                return;
            }
//            GameController.player = new PlayerController(0, 0);
//            GameController.level = new LevelController("res/map/map7.png");

        }

        for(int i=0; i < GameController.level.enemies.size(); i++){
            EnemyController en = GameController.level.enemies.get(i);
            if(en.intersects(this)){
                g.soundBG.stop();
                g.r1.suspend();
                new soundModel("res/sound/lose.wav", false).start();
                GameController.STATE = GameController.DIE_SCREEN;
                //GameController.STATE = GameController.LEVEL_PASSED;
                //GameController.STATE = GameController.WIN_SCREEN;
                new SaveScore(g.tm.getLastTime());
                g.tm.setZero();

            }
        }

        time++;
        if (time == targetTime) {
            time = 0;
            imageIndex++;
        }

    }

    private boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx, nexty, width, height);
        LevelController level = GameController.level;

        for(int xx=0; xx < level.tiles.length; xx++){
            for(int yy=0; yy < level.tiles[0].length; yy++){
                if(level.tiles[xx][yy] != null){
                    if(bounds.intersects(level.tiles[xx][yy])){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void render(Graphics g){
//        g.setColor(Color.CYAN);
//        g.fillRect(x, y, width, height);
        if (lastDir == 1) {
            g.drawImage(TextureModel.playerLR[imageIndex%2], x, y, 32, 32, null);
        } else if (lastDir == -1) {
            g.drawImage(TextureModel.playerLR[imageIndex%2], x+32, y, -32, 32, null);
        }
        else if (lastDir == 2) {
            g.drawImage(TextureModel.playerUD[imageIndex%2], x, y, 32, 32, null);
        }
        else if (lastDir == -2) {
            g.drawImage(TextureModel.playerUD[imageIndex%2], x, y+32, 32, -32, null);
        }

    }
}
