package Entity;

import java.awt.*;

public class Bullet extends MovingEntity {
    public Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(getX(), getY(), 5, 5);
    }
}
