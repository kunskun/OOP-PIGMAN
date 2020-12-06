package Controller;

import Model.TextureModel;


import java.awt.*;
import java.util.Random;

public class EnemyController extends Rectangle {
    private static final long serialVersionUID = 1L;
    private int random = 0, smart = 1, find_path = 2;
    private int state = random;
    private int right = 0, left = 1, up = 2, down = 3;
    private int dir = -1;
    public Random randomGen;
    private int time = 0;
    private int targetTime = 60*3;
    private int spd = 2;
    private int lastDir = -1;

    private int dirMove = 0;


    private int timeMove, targetTimeMove = 10;
    public  int imageIndex = 0;

    public EnemyController(int x, int y){
        randomGen = new Random();
        setBounds(x, y, 32, 32);
        dir = randomGen.nextInt(4);
    }

    public void tick(){
        if(state == random){
            if(dir == right){
                if(canMove(x+spd, y)){
                    x += spd;
                    dirMove = 0;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if(dir == left){
                if(canMove(x-spd, y)){
                    x -= spd;
                    dirMove = 1;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if(dir == up){
                if(canMove(x, y-spd)){
                    y -= spd;
                    dirMove = 2;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if(dir == down){
                if(canMove(x, y+spd)){
                    y += spd;
                    dirMove = 3;
                } else {
                    dir = randomGen.nextInt(4);
                }
            }

            time++;

            if(time == targetTime){
                state = smart;
                time = 0;
            }

        } else if(state == smart){
            //Follow the player
            boolean move = false;

            if(x < GameController.player.x){
                if(canMove(x+spd, y)){
                    x += spd;
                    move = true;
                    lastDir = right;
                    dirMove = 0;
                }
            }
            if(x > GameController.player.x){
                if(canMove(x-spd, y)){
                    x -= spd;
                    move = true;
                    lastDir = left;
                    dirMove = 1;
                }
            }
            if(y < GameController.player.y){
                if(canMove(x, y+spd)){
                    y += spd;
                    move = true;
                    lastDir = down;
                    dirMove = 3;
                }
            }
            if(y > GameController.player.y){
                if(canMove(x, y-spd)){
                    y -= spd;
                    move = true;
                    lastDir = up;
                    dirMove = 2;
                }
            }

            if(x == GameController.player.x && y == GameController.player.y){
                move = true;
            }

            if(!move){
                state = find_path;
            }
            time++;

            if(time == targetTime){
                state = random;
                time = 0;
            }
        } else if(state == find_path){

            if(lastDir == right){
                if(y < GameController.player.y){
                    if(canMove(x, y+spd)){
                        y += spd;
                        state = smart;

                    }
                } else {
                    if(canMove(x, y-spd)){
                        y -= spd;
                        state = smart;
                    }
                }
                if(canMove(x+spd, y)){
                    x += spd;
                    dirMove = 0;
                }
            } else if(lastDir == left){
                if(y < GameController.player.y){
                    if(canMove(x, y+spd)){
                        y += spd;
                        state = smart;
                    }
                } else {
                    if(canMove(x, y-spd)){
                        y -= spd;
                        state = smart;
                    }
                }
                if(canMove(x-spd, y)){
                    x -= spd;
                    dirMove = 1;
                }

            } else if(lastDir == up){
                if(x < GameController.player.x){
                    if(canMove(x+spd, y)){
                        x += spd;
                        state = smart;
                    }
                } else {
                    if(canMove(x-spd, y)){
                        x -= spd;
                        state = smart;
                    }
                }
                if(canMove(x, y+spd)){
                    y += spd;
                    dirMove = 3;
                }

            } else if(lastDir == down){
                if(x < GameController.player.x){
                    if(canMove(x+spd, y)){
                        x += spd;
                        state = smart;
                    }
                } else {
                    if(canMove(x-spd, y)){
                        x -= spd;
                        state = smart;
                    }
                }
                if(canMove(x, y-spd)){
                    y -= spd;
                    dirMove = 2;
                }
            }

            time++;

            if(time == targetTime){
                state = random;
                time = 0;
            }
        }
        timeMove++;
        if (timeMove == targetTimeMove) {
            timeMove = 0;
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
//        g.setColor(Color.RED);
//        g.fillRect(x, y, 32, 32);
        if (dirMove == right) {
            g.drawImage(TextureModel.tigerLR[imageIndex%2], x, y, 32, 32, null);
        }
        else if (dirMove == left) {
            g.drawImage(TextureModel.tigerLR[imageIndex%2], x+32, y, -32, 32, null);
        }
        else if (dirMove == up) {
            g.drawImage(TextureModel.tigerUD[imageIndex%2], x, y, 32, 32, null);
        }
        else if (dirMove == down) {
            g.drawImage(TextureModel.tigerUD[imageIndex%2], x, y+32, 32, -32, null);
        }

    }
}
