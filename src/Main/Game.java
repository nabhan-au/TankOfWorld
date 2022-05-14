package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import Entity.*;
import Entity.EntityList.Brick;
import Entity.EntityList.InvisibleBlock;
import Entity.EntityList.Steel;
import Entity.EntityList.Tree;
import Entity.Events.DomainEvent;
import Main.GameState.State;
import Main.GameState.TwoPlayerMode;
import Presentation.BrickUIObject;
import Presentation.InvisibleBlockUIObject;
import Presentation.SteelUIObject;
import Presentation.TankUIObject;
import Presentation.TreeUIObject;
import Presentation.UIObject;
import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.ImageSet;
import Presentation.ImageSet.TankImageSet;
import Presentation.ImageSet.TankImageSet.TankImage;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game extends JFrame implements PropertyChangeListener {
    private Map map;
    public static int BOARD_SIZE = 1000;
    public static int BLOCK_SIZE = 40;
    private Thread gameThread;
    private GamePanal gamePanal;
    private Boolean gameOver = false;
    private State state;
    public Game() {
        this.map = new Map(BOARD_SIZE, BOARD_SIZE, 2, this);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        this.gamePanal = new GamePanal(map);
        this.state = new TwoPlayerMode(this, map, BOARD_SIZE, BLOCK_SIZE);
        add(gamePanal);
        pack();
        gamePanal.requestFocus();
    }

    public void addKeyListener(KeyHandler keyHandler) {
        gamePanal.addKeyListener(keyHandler);
    }

    public void start() {
        setVisible(true);
        gameThread = new Thread() {
            public void run() {
                while (!gameOver) {
                    map.tick();
                    try {
                        sleep(1000/300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gamePanal.repaint();
                }
            }
        };
        gameThread.start();
    }

    public Map getMap() {
        return map;
    }

    public void gameOver() {
        gameOver = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == DomainEvent.GameOver.toString()) {
            this.gameOver = true;
        }
    }

    class GamePanal extends JPanel {

        public GamePanal(Map map) {
            super();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            state.paint(g);
        }
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
