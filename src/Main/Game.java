package Main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Entity.Events.DomainEvent;
import Main.GameState.Menu;
import Main.GameState.State;
import Main.GameState.TwoPlayerMode;
import Presentation.Layers.GameLayer;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game extends JFrame implements PropertyChangeListener {
    private Map map;
    public static boolean DEBUG = true;
    public static int BOARD_SIZE = 700;
    public static int BLOCK_SIZE = 40;
    private Thread gameThread;
    private Boolean gameOver = false;
    private State state;
    private JLayeredPane jLayeredPane;

    public Game() {
        this.map = new Map(BOARD_SIZE, BOARD_SIZE, 2, this);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setTitle("TankOfWorld");
        this.jLayeredPane = getLayeredPane();
        this.state = new Menu(this);
        pack();
        requestFocus();
    }

    public void addLayer(JPanel layer) {
        jLayeredPane.add(layer);
    }

    public void removeAllLayer() {
        jLayeredPane.removeAll();
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

    public void setState(State newState) {
        this.state = newState;
    }

    public void gameOver() {
        // TODO: Uncomment this line after testing complete
        // gameOver = true;
    }

    @Override
    public void repaint() {
        super.repaint();
        state.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == DomainEvent.GameOver.toString()) {
            this.gameOver();
        }
    }

    public GameLayer getEffectGameLayer() {
        return state.getEffectGameLayer();
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
