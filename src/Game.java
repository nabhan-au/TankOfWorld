import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;

import Entity.*;

import java.awt.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Game extends JFrame {
    private Map map;
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
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
                        sleep(5);
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
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            map.getTank(0).moveUp();
                            break;
                        case KeyEvent.VK_DOWN:
                            map.getTank(0).moveDown();
                            break;
                        case KeyEvent.VK_LEFT:
                            map.getTank(0).moveLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            map.getTank(0).moveRight();
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintElements(g);
        }

        public void paintElements(Graphics g) {
            List<Entity> entities = map.getEntities();

            for (Entity entity : entities) {
                int x = entity.getX();
                int y = entity.getY();
                if (entity instanceof Bullet) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, 5, 5);
                } else {
                    g.fillRect(x, y, 20, 20);
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
