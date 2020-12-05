package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

    private boolean isRunning = false;

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pig-Man";

    private Thread thread;
    public static int count=0;
    public static Player player;
    public static Level level;
    public static SpriteSheet spritesheet;
    public static SetScreen screen;
    public static final int PAUSE_SCREEN = 0, GAME = 1, DIE_SCREEN = 2, WIN_SCREEN = 3;
    public static int STATE = -1;

    public boolean isEnter = false;

    private int time = 0;
    private int targetFrames = 30;
    private boolean showText = true;


    public Game(){
        Dimension d = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        addKeyListener(this);
        addMouseListener(this);

        STATE = PAUSE_SCREEN;
        screen = new SetScreen("");

        spritesheet = new SpriteSheet("res/sprites/spritesheet.png");
        new Texture();
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

    private void tick() {
        if (STATE == GAME) {
            player.tick();
            level.tick();
        } else if (STATE == PAUSE_SCREEN) {

            if (isEnter) {
                isEnter = false;
                player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
                level = new Level("res/map/map9.png");
                STATE = GAME;
            }
        } else if (STATE == DIE_SCREEN) {
            if (isEnter) {
                isEnter = false;
                player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
                level = new Level("res/map/map9.png");
                STATE = GAME;
            }
        } else if (STATE == WIN_SCREEN){
            if (isEnter) {
                isEnter = false;
                player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
                level = new Level("res/map/map9.png");
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if(STATE == GAME) {
            player.render(g);
            level.render(g);
        } else if(STATE == PAUSE_SCREEN){
            screen = new SetScreen("res/load/load.jpg");
            g.drawImage(screen.getSprite(0,0), 0, 0, 640, 480, null);
        } else if(STATE == DIE_SCREEN) {
            screen = new SetScreen("res/load/los.png");
            g.drawImage(screen.getSprite(0, 0), 0, 0, 640, 480, null);
        } else if(STATE == WIN_SCREEN){
            screen = new SetScreen("res/load/jok.png");
            g.drawImage(screen.getSprite(0, 0), 0, 0, 640, 480, null);

        }
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

    public static void main(String[] args) {
	// write your code here
        Game game = new Game();
        JFrame f = new JFrame();
        f.setTitle(Game.TITLE);
        f.add(game);
        f.setResizable(false);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        f.setVisible(true);

        game.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(STATE == GAME) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.right = true;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.down = true;
            }
//        System.out.println("Press");
        }
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
