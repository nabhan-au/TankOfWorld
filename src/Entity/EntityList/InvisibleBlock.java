package Entity.EntityList;

import Entity.Entity;

public class InvisibleBlock extends Entity {

    public InvisibleBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isHit(Entity another) {
        return false;
    }

    @Override
    public boolean canCollision() {
        return true;
    }

}
