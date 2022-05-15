package Main.AI.ArtificialNotIntelligent;

import Entity.Direction;

public class MoveDownState extends ANIState {

    @Override
    public ANIState nextState() {
        return new MoveLeftState();
    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }

}
