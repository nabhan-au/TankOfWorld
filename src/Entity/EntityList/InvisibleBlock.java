package Entity.EntityList;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Entity;

public class InvisibleBlock extends Entity {

    public InvisibleBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean isHit(Entity another) {
        return false;
    }

}
