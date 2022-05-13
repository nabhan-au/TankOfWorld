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

    public Game() {
        this.map = new Map(BOARD_SIZE, BOARD_SIZE, 2, this);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));

        this.gamePanal = new GamePanal(map);
        add(gamePanal);
        pack();
        gamePanal.requestFocus();

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
        private List<UIObject> uiObjects = new ArrayList<UIObject>();
        private ImageSet floorImageSet = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Floor);

        public GamePanal(Map map) {
            super();
            // TODO: Temporary add the Tank Creator in the GamePanal.
            for (int i = 0; i < 2; i++) {
                uiObjects.add(new TankUIObject(map.getTank(i), TankImageSet.getTankImageSet(TankImage.A)));
            }

            // TODO: Temporary generate UIObject based on the type.
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

            addKeyListener(new KeyHandler(
                    map.getTank(0),
                    KeyEvent.VK_LEFT,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.VK_UP,
                    KeyEvent.VK_DOWN,
                    KeyEvent.VK_SPACE));

            addKeyListener(new KeyHandler(
                    map.getTank(1),
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_CONTROL));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
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
            for (int i = 0; i < BOARD_SIZE; i += BLOCK_SIZE) {
                for (int j = 0; j < BOARD_SIZE; j += BLOCK_SIZE) {
                    g.drawImage(floorImageSet.getUp(), i, j, BLOCK_SIZE, BLOCK_SIZE, null);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
