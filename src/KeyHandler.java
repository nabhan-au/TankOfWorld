import Entity.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private Tank tank;
    private int moveLeft;
    private int moveRight;
    private int moveUp;
    private int moveDown;

    protected KeyHandler(Tank tank, int moveLeft, int moveRight, int moveUp, int moveDown) {
        this.tank = tank;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.moveDown = moveDown;
        this.moveUp = moveUp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == moveUp) {
            tank.setDirection("up");
            tank.moveUp();
        } else if (e.getKeyCode() == moveDown) {
            tank.setDirection("down");
            tank.moveDown();
        } else if (e.getKeyCode() == moveLeft) {
            tank.setDirection("left");
            tank.moveLeft();
        } else if (e.getKeyCode() == moveRight) {
            tank.setDirection("right");
            tank.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == moveUp || e.getKeyCode() == moveDown || e.getKeyCode() == moveLeft || e.getKeyCode() == moveRight) {
            tank.setMovingDirection(0, 0);
        }
    }
}
