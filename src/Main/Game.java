package Main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entity.EntityList.Tank;
import Entity.Events.DomainEvent;
import Main.GameState.Menu;
import Main.GameState.State;
import Presentation.Layers.GameLayer;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game extends JFrame implements PropertyChangeListener {
    private Map map;
    public static boolean DEBUG = false;
    public static int BOARD_SIZE = 800;
    public static int BLOCK_SIZE = 40;
    private Thread gameThread;
    private Boolean gameOver = false;
    private State state;
    private JLayeredPane jLayeredPane;

    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setTitle("TankOfWorld");
        setVisible(true);
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
        gameOver = false;
        gameThread = new Thread() {
            public void run() {
                while (!gameOver) {
                    map.tick();
                    try {
                        sleep(1000 / 200);
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
        if (DEBUG) {
            return;
        }
        gameOver = true;
        Tank winTank = null;
        for (Tank tank : map.getTankList()) {
            if (!tank.getIsRemovable()) {
                winTank = tank;
                break;
            }
        }
        String message = "";
        if (winTank == null) {
            message = "Unknown player win the game :(";
        } else {
            message = "Player " + winTank.getTankName() + " win!";
        }
        JOptionPane.showMessageDialog(this, message, "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        this.setState(new Menu(this));
    }

    public void setMap(MapData mapData) {
        setTitle("TankOfWorld: Map - " + mapData.getMapName());
        setPreferredSize(new Dimension(mapData.getMapSize() * Game.BLOCK_SIZE, mapData.getMapSize() * Game.BLOCK_SIZE));
        this.map = new Map(mapData, 2, this);
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
        new Game();
    }
}
