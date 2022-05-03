package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import Entity.*;

import java.awt.*;

import java.awt.event.KeyEvent;

public class Game extends JFrame {
    private Map map;
    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;
    private Thread gameThread;
    private GamePanal gamePanal;

    public Game() {
        this.map = new Map(1000, 1000, 2);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

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
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintElements(g);
        }

        public void paintElements(Graphics g) {
            List<Entity> entities = map.getEntities();
            for (Entity entity : entities) {
                entity.paint(g);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}