package Main.AI.ArtificialNotIntelligent;

import Entity.Direction;

public class MoveUpState extends ANIState {

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public ANIState nextState() {
        return new MoveRightState();
    }

}
