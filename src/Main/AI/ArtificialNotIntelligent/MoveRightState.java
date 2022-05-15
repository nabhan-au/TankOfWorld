package Main.AI.ArtificialNotIntelligent;

import Entity.Direction;

public class MoveRightState extends ANIState {

    @Override
    public Direction getDirection() {
        return Direction.RIGHT;
    }

    @Override
    public ANIState nextState() {
        return new MoveDownState();
    }

}
