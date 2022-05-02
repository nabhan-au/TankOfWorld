package Entity;

public class Tank extends MovingEntity {
    private String direction = "up";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

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

    public void stopMove() {
        this.setMovingDirection(0, 0);
    }

}
