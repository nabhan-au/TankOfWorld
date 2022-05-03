import Entity.Direction;
import Entity.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private Tank tank;
    private int moveLeft;
    private int moveRight;
    private int moveUp;
    private int moveDown;
    private int shoot;

    protected KeyHandler(Tank tank, int moveLeft, int moveRight, int moveUp, int moveDown, int shoot) {
        this.tank = tank;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.moveDown = moveDown;
        this.moveUp = moveUp;
        this.shoot = shoot;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == moveUp) {
            tank.setDirection(Direction.UP);
        } else if (e.getKeyCode() == moveDown) {
            tank.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == moveLeft) {
            tank.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == moveRight) {
            tank.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == shoot) {
            // TODO: Implement the shoot logic.
        }
        tank.move();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == moveUp || e.getKeyCode() == moveDown || e.getKeyCode() == moveLeft
                || e.getKeyCode() == moveRight) {
            tank.stop();
        }
    }
}
