package Main.GameState;

import Presentation.Layers.GameLayer;

public abstract class State {
    public abstract void repaint();
    public abstract GameLayer getEffectGameLayer();
}
