package Main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Entity.*;
import Entity.EntityList.Brick;
import Entity.EntityList.InvisibleBlock;
import Entity.EntityList.Steel;
import Entity.EntityList.Tree;
import Entity.Events.DomainEvent;
import Presentation.BrickUIObject;
import Presentation.InvisibleBlockUIObject;
import Presentation.SteelUIObject;
import Presentation.TankInfoUIObject;
import Presentation.TankUIObject;
import Presentation.TreeUIObject;
import Presentation.ImageSet.TankImageSet;
import Presentation.ImageSet.TankImageSet.TankImage;
import Presentation.Layers.FloorGameLayer;
import Presentation.Layers.GameInfoLayer;
import Presentation.Layers.GameLayer;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game extends JFrame implements PropertyChangeListener {
    private Map map;
    public static boolean DEBUG = true;
    public static int BOARD_SIZE = 700;
    public static int BLOCK_SIZE = 40;
    private Thread gameThread;
    private Boolean gameOver = false;
    private JPanel floorGameLayer = new FloorGameLayer();
    private GameLayer tankGameLayer = new GameLayer();
    private GameLayer blockGameLayer = new GameLayer();
    private GameLayer effectGameLayer = new GameLayer();
    private GameInfoLayer infoGameLayer = new GameInfoLayer();

    public Game() {
        this.map = new Map(BOARD_SIZE, BOARD_SIZE, 2, this);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setTitle("TankOfWorld");

        // TODO: Temporary add the Tank Creator in the GamePanal.
        TankUIObject tankUIObjectA = new TankUIObject(map.getTank(0), TankImageSet.getTankImageSet(TankImage.A), this);
        TankUIObject tankUIObjectB = new TankUIObject(map.getTank(1), TankImageSet.getTankImageSet(TankImage.B), this);
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
            } else if (entity instanceof InvisibleBlock && DEBUG) {
                blockGameLayer.addUIObject(new InvisibleBlockUIObject(entity));
            }
        }

        JLayeredPane jLayeredPane = getLayeredPane();

        jLayeredPane.add(infoGameLayer);
        jLayeredPane.add(effectGameLayer);
        jLayeredPane.add(tankGameLayer);
        jLayeredPane.add(blockGameLayer);
        jLayeredPane.add(floorGameLayer);
        pack();

        addKeyListener(new KeyHandler(
                map.getTank(0),
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_CONTROL));

        addKeyListener(new KeyHandler(
                map.getTank(1),
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_SPACE));

        requestFocus();

    }

    public void start() {
        setVisible(true);
        gameThread = new Thread() {
            public void run() {
                while (!gameOver) {
                    map.tick();
                    try {
                        sleep(1000 / 300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        };
        gameThread.start();
    }

    public Map getMap() {
        return map;
    }

    public void gameOver() {
        // TODO: Uncomment this line after testing complete
        // gameOver = true;
    }

    @Override
    public void repaint() {
        super.repaint();
        floorGameLayer.repaint();
        tankGameLayer.repaint();
        effectGameLayer.repaint();
        blockGameLayer.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == DomainEvent.GameOver.toString()) {
            this.gameOver();
        }
    }

    public GameLayer getEffectGameLayer() {
        return this.effectGameLayer;
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
