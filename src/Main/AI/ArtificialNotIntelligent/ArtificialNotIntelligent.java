package Main.AI.ArtificialNotIntelligent;

import Entity.EntityList.Tank;
import Main.Map;
import Main.AI.AIKeyController;

public class ArtificialNotIntelligent extends AIKeyController {
    private ANIState state = new MoveUpState();

    public ArtificialNotIntelligent(Tank tank, Map map) {
        super(tank, map);
        this.state = new MoveUpState();
        this.startAI();
    }

    @Override
    public void doAction() {
        if (!this.state.action(getTank())) {
            this.state = this.state.nextState();
        }
    }

}
