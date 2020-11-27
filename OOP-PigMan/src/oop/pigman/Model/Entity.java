package oop.pigman.Model;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle bounds;

    public Entity(int x, int y, int width, int height){
        this.x = x * Tile.TILESIZE;
        this.y = y * Tile.TILESIZE;
        this.width = width * Tile.TILESIZE;
        this.height = height * Tile.TILESIZE;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public int getLogicalX(){
        return (int)getX()/Tile.TILESIZE;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getLogicalY(){
        return (int)getY()/Tile.TILESIZE;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)(x + bounds.x), (int)(y + bounds.y), width, height);
    }
}
