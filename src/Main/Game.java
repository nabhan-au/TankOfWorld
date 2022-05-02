package Main;

import javax.swing.JFrame;

import Entity.Tank;
import GUI.GameUI;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int framePerSec = 60;
    private Tank tank;
    private List<KeyHandler> keyHandlers = new ArrayList();

    public void run() {
        tank = new Tank(50, 50);
        keyHandlers.add(new KeyHandler(tank, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN));

        GameUI gameUI = new GameUI(this);
        GameThread thread = new GameThread(gameUI, framePerSec);
        thread.run();
    }

    public List<KeyHandler> getKeyHandlers() {
        return keyHandlers;
    }

    public void tick() {

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

    public class GameThread extends Thread {
        private int framePerSec;
        private GameUI gui;

        public GameThread(GameUI gui, int framePerSec) {
            this.gui = gui;
            this.framePerSec = framePerSec;
        }

        @Override
        public void run() {
            while (true) {
                tick();
                gui.repaint();
                try {
                    Thread.sleep(1000/framePerSec);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
