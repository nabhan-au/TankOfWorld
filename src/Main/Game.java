package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import Entity.*;
import Entity.ImageSet.BlockImageSet;
import Entity.ImageSet.ImageSet;

import java.awt.*;

import java.awt.event.KeyEvent;

public class Game extends JFrame {
    private Map map;
    public static int BOARD_SIZE = 1000;
    public static int BLOCK_SIZE = 40;
    private Thread gameThread;
    private GamePanal gamePanal;

    public Game() {
        this.map = new Map(BOARD_SIZE, BOARD_SIZE, 2);
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
                while (true) {
                    map.tick();
                    try {
                        sleep(1000 / 60);
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

    class GamePanal extends JPanel {
        private Map map;
        private ImageSet floorImageSet = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Floor);

        public GamePanal(Map map) {
            super();
            this.map = map;
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
                    KeyEvent.VK_SPACE));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintFloor(g);
            paintElements(g);
        }

        public void paintElements(Graphics g) {
            List<Entity> entities = map.getEntities();
            for (Entity entity : entities) {
                entity.paint(g);
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
