package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {

    private boolean isRunning = false;

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pig-Man";

    private Thread thread;

    public static Player player;
    public static Level level;

    public Game(){
        Dimension d = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        addKeyListener(this);
        player = new  Player(Game.WIDTH/2, Game.HEIGHT/2);
        level = new Level("res/map/map3.png");
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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {player.right = true;}
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {player.left = true;}
        else if(e.getKeyCode() == KeyEvent.VK_UP) {player.up = true;}
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {player.down = true;}
        System.out.println("Press");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {player.right = false;}
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {player.left = false;}
        else if(e.getKeyCode() == KeyEvent.VK_UP) {player.up = false;}
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {player.down = false;}
        System.out.println("Release");
    }
}
