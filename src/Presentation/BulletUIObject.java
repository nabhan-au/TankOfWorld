package Presentation;

import java.awt.*;

import Entity.Entity;

public class BulletUIObject extends UIObject {

    public BulletUIObject(Entity bullet) {
        super(bullet);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(this.getEntity().getX(), this.getEntity().getY(), 10, 10);
    }

}
