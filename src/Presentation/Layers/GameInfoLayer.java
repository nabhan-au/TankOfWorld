package Presentation.Layers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.*;

import Main.Game;
import Presentation.UIObject;

public class GameInfoLayer extends JPanel {
    public static int GAME_INFO_PANAL_SIZE = 60;
    private List<UIObject> uiObjects = new ArrayList<UIObject>();

    public GameInfoLayer() {
        setBounds(0, 0, Game.BOARD_SIZE, GAME_INFO_PANAL_SIZE);
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
