package Main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Entity.EntityList.*;
import Main.Game;
import Main.KeyHandler;
import Main.Map;
import Presentation.*;
import Presentation.UIObject;
import Presentation.ImageSet.*;
import Presentation.ImageSet.TankImageSet.TankImage;

public class TwoPlayerMode  extends State{
    private List<UIObject> uiObjects = new ArrayList<UIObject>();
    private ImageSet floorImageSet = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Floor);
    private int boardSize;
    private int blockSize;

    public TwoPlayerMode(Game StateOwner, Map map, int boardSize, int blockSize) {
        this.boardSize = boardSize;
        this.blockSize = blockSize;
        for (int i = 0; i < 2; i++) {
            uiObjects.add(new TankUIObject(map.getTank(i), TankImageSet.getTankImageSet(TankImage.A)));
        }

        for (Entity entity : map.getEntities()) {

            if (entity instanceof Brick) {
                uiObjects.add(new BrickUIObject(entity));
            } else if (entity instanceof Tree) {
                uiObjects.add(new TreeUIObject(entity));
            } else if (entity instanceof Steel) {
                uiObjects.add(new SteelUIObject(entity));
            } else if (entity instanceof InvisibleBlock) {
                uiObjects.add(new InvisibleBlockUIObject(entity));
            }
        }

        StateOwner.addKeyListener(new KeyHandler(
                map.getTank(0),
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_SPACE));

        StateOwner.addKeyListener(new KeyHandler(
                map.getTank(1),
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_SPACE));
    }

    public void paint(Graphics g) {
        paintFloor(g);
        paintUIObjects(g);
    }

    public void paintUIObjects(Graphics g) {
        for (int i = uiObjects.size() - 1; i > -1; i--) {
            UIObject uiObject = uiObjects.get(i);
            if (uiObject.getIsRemovable()) {
                uiObjects.remove(i);
            } else {
                uiObject.paint(g);
            }
        }
    }

    public void paintFloor(Graphics g) {
        for (int i = 0; i < boardSize; i += blockSize) {
            for (int j = 0; j < boardSize; j += blockSize) {
                g.drawImage(floorImageSet.getUp(), i, j, blockSize, blockSize, null);
            }
        }
    }

}