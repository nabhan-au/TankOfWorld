package Entity;

public class Tank extends MovingEntity {

    public Tank(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        this.setMovingDirection(0, -1);
    }

    public void moveLeft() {
        this.setMovingDirection(-1, 0);
    }

    public void moveRight() {
        this.setMovingDirection(1, 0);
    }

    public void moveDown() {
        this.setMovingDirection(0, 1);
    }

}
