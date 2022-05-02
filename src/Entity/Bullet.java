package Entity;

import java.awt.*;
import java.util.List;

public class Bullet extends MovingEntity {
    public Bullet(int x, int y, int dx, int dy) {
        super(x, y, dx, dy);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), 5, 5);
    }
}
