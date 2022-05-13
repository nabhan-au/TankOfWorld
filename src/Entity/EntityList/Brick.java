package Entity.EntityList;

import Entity.Entity;

public class Brick extends Entity {
    public Brick(int x, int y, int width, int height) {
        super(x, y);
        setSize(width, height);
    }

    @Override
    public void onHit() {
        super.onHit();
        this.flagToBeRemove();
    }
}
