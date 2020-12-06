package Controller;

import Model.SpriteSheetModel;
import Model.TextureModel;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GameController extends Canvas implements Runnable, KeyListener{
    private boolean isRunning = false;

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pig-Man";

    private Thread thread;

    public static PlayerController player;
    public static LevelController level;
    public static SpriteSheetModel spritesheet;

    public GameController(){
        Dimension d = new Dimension(GameController.WIDTH, GameController.HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        addKeyListener(this);

        player = new  PlayerController(GameController.WIDTH/2, GameController.HEIGHT/2);
        level = new LevelController("res/map/map9.png");
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
        player.tick();
        level.tick();

    }

    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs  == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameController.WIDTH, GameController.HEIGHT);
        player.render(g);
        level.render(g);
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

}
