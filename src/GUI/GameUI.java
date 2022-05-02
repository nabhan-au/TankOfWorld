package GUI;

import Main.Game;
import Main.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameUI extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    private GamePanal gamePanal;
    private Game game;

    public GameUI(Game game) {
        // Init Main.Game Panal
        super();
        gamePanal = new GamePanal();

        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gamePanal);
        addKeyListener(game.getKeyHandlers().get(0));
        pack();

        setVisible(true);

    }

    public void setKeyHandler(List<KeyHandler> keyHandlers) {
        for (KeyHandler keyHandler: keyHandlers) {
            addKeyListener(keyHandler);
        }
    }

}
