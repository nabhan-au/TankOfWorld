package Entity.EntityList;

import Entity.Entity;

public class Tree extends Entity {

    public Tree(int x, int y, int width, int height) {
        super(x, y);
        setSize(width, height);
    }

    @Override
    public boolean isHit(Entity another) {
        return false;
    }

}
