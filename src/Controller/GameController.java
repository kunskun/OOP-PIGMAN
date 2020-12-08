package Controller;

import Model.SpriteSheetModel;
import Model.TextureModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class GameController extends Canvas implements Runnable, KeyListener, MouseListener {
    private boolean isRunning = false;

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pig-Man";

    private Thread thread;

    public static PlayerController player;
    public static LevelController level;
    public static SpriteSheetModel spritesheet;
    public static final int PAUSE_SCREEN = 0, GAME = 1, DIE_SCREEN = 2, WIN_SCREEN = 3, LEVEL_PASSED = 4;
    public static int STATE = -1;

    public boolean isEnter = false;

    private int time = 0;
    private int targetFrames = 30;
    private boolean showText = true;
    public static int count = 0;

    public GameController(){
        Dimension d = new Dimension(GameController.WIDTH, GameController.HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        addKeyListener(this);
        addMouseListener(this);

        STATE = PAUSE_SCREEN;
        //screen = new SetScreen("");

//        player = new  PlayerController(GameController.WIDTH/2, GameController.HEIGHT/2);
//        level = new LevelController("res/map/map9.png");
        spritesheet = new SpriteSheetModel("res/sprites/spritesheet.png");

        new TextureModel();
    }

    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void tick(){
//        player.tick();
//        level.tick();
        if (STATE == GAME) {
            player.tick();
            level.tick();
        } else if (STATE == PAUSE_SCREEN) {

            if (isEnter) {
                isEnter = false;
                player = new PlayerController(GameController.WIDTH / 2, GameController.HEIGHT / 2);
                level = new LevelController("res/map/map9.png");
                STATE = GAME;
            }
        } else if (STATE == DIE_SCREEN) {
            if (isEnter) {
                isEnter = false;
                player = new PlayerController(GameController.WIDTH / 2, GameController.HEIGHT / 2);
                level = new LevelController("res/map/map9.png");
                STATE = GAME;
            }
        } else if (STATE == WIN_SCREEN){
            if (isEnter) {
                isEnter = false;
                player = new PlayerController(GameController.WIDTH / 2, GameController.HEIGHT / 2);
                level = new LevelController("res/map/map9.png");
                STATE = GAME;
            }
        } else if (STATE == LEVEL_PASSED){
            if (isEnter) {
                isEnter = false;
                player = new PlayerController(GameController.WIDTH / 2, GameController.HEIGHT / 2);
                level = new LevelController("res/map/map7.png");
                STATE = GAME;
            }
        }

    }

    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs  == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
//        g.setColor(Color.BLACK);
        g.drawImage(TextureModel.groundGlass1,0,0, GameController.WIDTH, GameController.HEIGHT, null);
        //g.fillRect(0, 0, GameController.WIDTH, GameController.HEIGHT);
        if(STATE == GAME) {
            player.render(g);
            level.render(g);
        } else if(STATE == PAUSE_SCREEN){
            //screen = new SpriteSheetModel("res/load/load.jpg");
            g.drawImage(TextureModel.imgLoad, 0, 0, 640, 480, null);
        } else if(STATE == DIE_SCREEN) {
            //screen = new SetScreen("res/load/los.png");
            g.drawImage(TextureModel.imgLos, 0, 0, 640, 480, null);
            g.setFont(new Font("Android Euclid", Font.BOLD, 24));
            g.drawString("12", 300, 220);
        } else if(STATE == WIN_SCREEN){
            //screen = new SetScreen("res/load/jok.png");
            g.drawImage(TextureModel.imgWin, 0, 0, 640, 480, null);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
            g.drawString("1254896", 100, 100);

        } else if(STATE == LEVEL_PASSED){
            g.drawImage(TextureModel.imgWin, 0, 0, 640, 480, null);

        }

//        level.render(g);
//        player.render(g);
        g.dispose();
        bs.show();
    }

    @Override
    public void run(){

        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1000000000 / targetTick;

        while(isRunning){
            long now = System.nanoTime();

            delta += (now - lastTime) / ns;
            //System.out.println(delta);
            lastTime = now;

            while (delta >= 1){
                tick();
                render();
                fps++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }

        }

        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {player.right = true;}
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {player.left = true;}
        else if(e.getKeyCode() == KeyEvent.VK_UP) {player.up = true;}
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {player.down = true;}
//        System.out.println("Press");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {player.right = false;}
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {player.left = false;}
        else if(e.getKeyCode() == KeyEvent.VK_UP) {player.up = false;}
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {player.down = false;}
//        System.out.println("Release");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(STATE == PAUSE_SCREEN) {
            isEnter = true;
        }
        if(STATE == DIE_SCREEN){
            isEnter = true;
        }
        if(STATE == WIN_SCREEN){
            isEnter = true;
        }
        if(STATE == LEVEL_PASSED){
            isEnter = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
