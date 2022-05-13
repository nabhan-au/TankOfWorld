package Entity;

import Main.Game;

public abstract class Entity {
    private int x;
    private int y;
    private int width = Game.BLOCK_SIZE;
    private int height = Game.BLOCK_SIZE;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void onHit() {
        System.out.println("Hit! Detected!");
    };

    public boolean isHit(Entity another) {
        return (getX() < another.getX() + another.getWidth()
                && getX() + getWidth() > another.getX()
                && getY() < another.getY() + another.getHeight()
                && getY() + getHeight() > another.getY());

    }

    public void animate() {
    }
}
