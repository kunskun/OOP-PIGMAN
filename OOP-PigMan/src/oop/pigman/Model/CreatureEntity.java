package oop.pigman.Model;

import oop.pigman.Controller.World;

import java.util.List;

public abstract class CreatureEntity extends Entity{
    private static float BOUNDS_COVER = 0.80f;

    public static final int DEFAULT_HEALT = 10;
    public static final float DEFAULT_SPEED = 3.0f;

    public enum Direction { UP, DOWN, LEFT, RIGHRT};
    protected Direction dir;

    private float lastX, lastY;
    private float dx, dy;

    protected int health;
    protected float speed;
    protected World world;

    public CreatureEntity(int x, int y, int width, int height, World w){
        super(x, y, width, height);
        health = DEFAULT_HEALT;
        speed = DEFAULT_SPEED;
        lastX = x;
        lastY = y;
        world = w;

        float offset = Tile.TILESIZE * (1-BOUNDS_COVER);
        bounds.x = (int)(offset/2);
        bounds.y = (int)(offset/2);
        bounds.width = (int)(Tile.TILESIZE * BOUNDS_COVER);
        bounds.height = (int)(Tile.TILESIZE * BOUNDS_COVER);
    }

}
