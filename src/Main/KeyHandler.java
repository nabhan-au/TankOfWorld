package Main;

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
            tank.moveUp();
            System.out.println(tank.getX());
            System.out.println(tank.getY());
        } else if (e.getKeyCode() == moveDown) {
            tank.moveDown();
            System.out.println(tank.getX());
            System.out.println(tank.getY());
        } else if (e.getKeyCode() == moveLeft) {
            tank.moveLeft();
        } else if (e.getKeyCode() == moveDown) {
            tank.moveDown();
        }
    }
}
