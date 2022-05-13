package Presentation;

import java.awt.*;

import Entity.EntityList.Bullet;

public class BulletUIObject implements UIObject {
    private Bullet bullet;

    public BulletUIObject(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(bullet.getX(), bullet.getY(), 10, 10);
    }

}
