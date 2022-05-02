package command;

import Entity.Tank;

public class CommandMoveLeft extends Command{

    public CommandMoveLeft(Tank tank) {
        super(tank);
    }

    @Override
    public void execute() {
        getTank().moveLeft();
    }
}
