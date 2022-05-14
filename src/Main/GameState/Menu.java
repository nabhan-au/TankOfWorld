package Main.GameState;

import Main.Game;
import Main.GameState.State;
import Presentation.Layers.GameLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends State {
    private Game stateOwner;
    private static int TILE_SIZE = 100;
    private MenuLayer menuLayer = new MenuLayer();

    public Menu(Game stateOwner) {
        this.stateOwner = stateOwner;
        stateOwner.removeAllLayer();
        stateOwner.addLayer(menuLayer);
        stateOwner.addKeyListener(new Controller());
    }

    @Override
    public void repaint() {
        menuLayer.repaint();

    }

    @Override
    public GameLayer getEffectGameLayer() {
        return null;
    }

    class MenuLayer extends JPanel {

        public MenuLayer() {
            setBounds(0, 0, Game.BOARD_SIZE, Game.BOARD_SIZE);
        }

        @Override
        public void paint(Graphics g) {
            g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Tank Of World";
            int x = getXforCenterText(g, text, g.getFont(), 0, Game.BOARD_SIZE);
            int y = TILE_SIZE;

            g.setColor(Color.black);
            g.drawString(text, x, y);

            g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
            text = "SINGLE PLAYER";
            x = getXforCenterText(g, text, g.getFont(), 0, Game.BOARD_SIZE);
            y = TILE_SIZE * 3;
            g.drawString(text, x, y);
        }

        public int getXforCenterText(Graphics g,String text, Font font, int x, int width) {
            FontMetrics metrics = g.getFontMetrics(font);
            int size = metrics.stringWidth(text);
            return x + (width - size) / 2;
        }
    }

    class Controller extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                stateOwner.setState(new TwoPlayerMode(stateOwner));
            }
        }
    }
}