package Main.GameState;

import Main.Game;
import Main.GameState.State;

import java.awt.*;

public class Menu extends State {
    private Game stateOwner;
    private static int TILE_SIZE = 100;

    public Menu(Game stateOwner) {
        this.stateOwner = stateOwner;
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Tank Of World";
        int x = getXforCenterText(g, text, g.getFont(), 0, stateOwner.getBoardSize());
        int y = TILE_SIZE;

        g.setColor(Color.black);
        g.drawString(text, x, y);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
        text = "SINGLE PLAYER";
        x = getXforCenterText(g, text, g.getFont(), 0, stateOwner.getBoardSize());
        y = TILE_SIZE * 3;
        g.drawString(text, x, y);
    }

    public int getXforCenterText(Graphics g,String text, Font font, int x, int width) {
        FontMetrics metrics = g.getFontMetrics(font);
        int size = metrics.stringWidth(text);
        return x + (width - size) / 2;
    }
}