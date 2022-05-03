import Entity.Direction;
import Entity.EntityList.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private Tank tank;
    private int moveLeft;
    private int moveRight;
    private int moveUp;
    private int moveDown;
    private int shoot;
    private int currentKeyPress;

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
            currentKeyPress = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == moveDown) {
            tank.setDirection(Direction.DOWN);
            currentKeyPress = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == moveLeft) {
            tank.setDirection(Direction.LEFT);
            currentKeyPress = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == moveRight) {
            tank.setDirection(Direction.RIGHT);
            currentKeyPress = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == shoot) {
            tank.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() == moveUp && currentKeyPress == e.getKeyCode())
                || (e.getKeyCode() == moveDown && currentKeyPress == e.getKeyCode())
                || (e.getKeyCode() == moveLeft && currentKeyPress == e.getKeyCode())
                || (e.getKeyCode() == moveRight && currentKeyPress == e.getKeyCode())) {
            tank.stop();
        }
    }
}
