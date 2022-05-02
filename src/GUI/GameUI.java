package GUI;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    private GamePanal gamePanal;

    public GameUI() {
        // Init Game Panal
        gamePanal = new GamePanal();

        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gamePanal);
        pack();

        setVisible(true);

    }

}
