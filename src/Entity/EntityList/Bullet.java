package Entity.EntityList;

import java.awt.*;

import Entity.MovingEntity;

public class Bullet extends MovingEntity {
    public static int BULLET_SPEED = 10;

    public Bullet() {
        super(-1, -1, BULLET_SPEED);
    }

    public Bullet(int x, int y) {
        super(x, y, BULLET_SPEED);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(getX(), getY(), 10, 10);
    }
}
