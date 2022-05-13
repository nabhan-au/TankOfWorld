package Entity.EntityList;

import Entity.MovingEntity;

public class Bullet extends MovingEntity {
    public static int BULLET_SPEED = 10;

    public Bullet() {
        super(-1, -1, BULLET_SPEED);
        this.setSize(1, 1);
    }

    public Bullet(int x, int y) {
        super(x, y, BULLET_SPEED);
    }
}
