package Main.AI.ArtificialNotIntelligent;

import Entity.Direction;
import Entity.EntityList.Tank;

public abstract class ANIState {

    private int count = 0;

    public ANIState() {
    }

    abstract public Direction getDirection();

    public boolean action(Tank tank) {
        if (count == 30) {
            tank.stop();
            return false;
        } else if (count == 0) {
            tank.setDirection(getDirection());
            tank.move();
        }
        if (count % 5 == 0) {
            tank.shoot();
        }
        count++;
        return true;
    }

    abstract public ANIState nextState();
}
