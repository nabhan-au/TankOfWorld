package Main.GameState;

import java.awt.event.KeyEvent;
import java.util.List;

import Entity.Entity;
import Entity.EntityList.*;
import Main.Game;
import Main.KeyHandler;
import Main.Map;
import Main.AI.ArtificialNotIntelligent.ArtificialNotIntelligent;
import Presentation.*;
import Presentation.Layers.FloorGameLayer;
import Presentation.Layers.GameInfoLayer;
import Presentation.Layers.GameLayer;
import Presentation.ImageSet.*;
import Presentation.ImageSet.TankImageSet.TankImage;

import javax.swing.*;

public class PlayingMode extends State {
    private JPanel floorGameLayer = new FloorGameLayer();
    private GameLayer tankGameLayer = new GameLayer();
    private GameLayer blockGameLayer = new GameLayer();
    private GameLayer effectGameLayer = new GameLayer();
    private GameInfoLayer infoGameLayer = new GameInfoLayer();
    private Map map;

    public PlayingMode(Game stateOwner, int numPlayer, List<String> playerNames) {
        this.map = stateOwner.getMap();

        TankUIObject tankUIObjectA = new TankUIObject(map.createNewPlayer(playerNames.get(0), stateOwner),
                TankImageSet.getTankImageSet(TankImage.A),
                stateOwner);
        TankUIObject tankUIObjectB = new TankUIObject(map.createNewPlayer(playerNames.get(1), stateOwner),
                TankImageSet.getTankImageSet(TankImage.B),
                stateOwner);
        tankGameLayer.addUIObject(tankUIObjectA);
        tankGameLayer.addUIObject(tankUIObjectB);
        infoGameLayer.addUIObject(new TankInfoUIObject(tankUIObjectA, 10, 10, 40));
        infoGameLayer.addUIObject(new TankInfoUIObject(tankUIObjectB, 400, 10, 40));

        // Generate the BlockUIObject and add it to blockGameLayer.
        for (Entity entity : map.getEntities()) {
            if (entity instanceof Brick) {
                blockGameLayer.addUIObject(new BrickUIObject(entity));
            } else if (entity instanceof Tree) {
                blockGameLayer.addUIObject(new TreeUIObject(entity));
            } else if (entity instanceof Steel) {
                blockGameLayer.addUIObject(new SteelUIObject(entity));
            } else if (entity instanceof InvisibleBlock && Game.DEBUG) {
                blockGameLayer.addUIObject(new InvisibleBlockUIObject(entity));
            }
        }
        stateOwner.removeAllLayer();
        stateOwner.addLayer(infoGameLayer);
        stateOwner.addLayer(effectGameLayer);
        stateOwner.addLayer(blockGameLayer);
        stateOwner.addLayer(tankGameLayer);
        stateOwner.addLayer(floorGameLayer);

        stateOwner.addKeyListener(new KeyHandler(
                map.getTank(0),
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_CONTROL));
        if (numPlayer == 1) {
            new ArtificialNotIntelligent(map.getTank(1), map);
        } else {
            stateOwner.addKeyListener(new KeyHandler(
                    map.getTank(1),
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_SPACE));
        }

    }

    @Override
    public void repaint() {
        floorGameLayer.repaint();
        tankGameLayer.repaint();
        effectGameLayer.repaint();
        blockGameLayer.repaint();
    }

    @Override
    public GameLayer getEffectGameLayer() {
        return effectGameLayer;
    }
}