package Entity;

import java.awt.*;
import java.util.List;

public abstract class Entity {
    private int x;
    private int y;
    private int width = 1;
    private int height = 1;

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

    public boolean checkOverlap(int buttomLeftX1, int buttomLeftY1, int topRigtX1, int topRigtY1, int buttomLeftX2,
            int buttomLeftY2, int topRigtX2, int topRigtY2) {
        if (topRigtY1 < buttomLeftY2
                || buttomLeftY1 > topRigtY2) {
            return false;
        }
        if (topRigtX1 < buttomLeftX2
                || buttomLeftX1 > topRigtX2) {
            return false;
        }
        return true;
    }

    public boolean isHit(Entity another) {
        return checkOverlap(x, y, x + width, y + height, another.getX(), another.getY(),
                another.getX() + another.getWidth(), another.getY() + another.getHeight());
    }

    public void animate() {
    }

    abstract public void paint(Graphics g);
}
