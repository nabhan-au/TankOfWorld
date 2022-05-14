package Presentation.Layers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.*;

import Main.Game;
import Presentation.UIObject;

public class GameLayer extends JPanel {

    private List<UIObject> uiObjects = new ArrayList<UIObject>();

    public GameLayer() {
        setOpaque(false);
        this.setBounds(0, 0, Game.BOARD_SIZE + Game.BLOCK_SIZE, Game.BOARD_SIZE + Game.BLOCK_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
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

    public void addUIObject(UIObject uiObject) {
        this.uiObjects.add(uiObject);
    }

}
