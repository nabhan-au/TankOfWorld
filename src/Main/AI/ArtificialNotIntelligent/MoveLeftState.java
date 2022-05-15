package Main.AI.ArtificialNotIntelligent;

import Entity.Direction;

public class MoveLeftState extends ANIState {
    @Override
    public Direction getDirection() {
        return Direction.LEFT;
    }

    @Override
    public ANIState nextState() {
        return new MoveUpState();
    }

}
