package Controller;

import Model.TextureModel;


import java.awt.*;

public class PlayerController extends Rectangle {
    private static final long serialVersionUID = 1L;

    public boolean right, left, up, down;
    private int speed = 4;

    public PlayerController(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void tick(){
        if(right && canMove(x+speed, y)) {x += speed;}
        if(left && canMove(x-speed, y)) {x -= speed;}
        if(up && canMove(x, y-speed)) {y -= speed;}
        if(down && canMove(x, y+speed)) {y += speed;}

        LevelController level = GameController.level;

        for(int i=0; i < level.apples.size(); i++){
            if(this.intersects(level.apples.get(i))){
                level.apples.remove(i);
                break;
            }
        }

        if(level.apples.size() == 0){
            //Game end when you eat all apple
            GameController.player = new PlayerController(0, 0);
            GameController.level = new LevelController("res/map/map7.png");
            return;

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
        g.drawImage(TextureModel.player, x, y, 32, 32, null);
    }
}
