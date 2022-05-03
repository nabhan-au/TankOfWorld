package Entity;

import Main.Game;

public abstract class MovingEntity extends Entity {
    private Direction direction;
    private int speed;
    private boolean isMoving = false;

    public MovingEntity(int x, int y) {
        this(x, y, 1);
        this.direction = Direction.UP;
    }

    public MovingEntity(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void move() {
        this.isMoving = true;
    }

    public void stop() {
        this.isMoving = false;
    }

    @Override
    public void animate() {
        if (!isMoving) {
            return;
        }
        int newX = this.getX() + (speed * direction.getX());
        int newY = this.getY() + (speed * direction.getY());

        if (newX <= 0
                || newX + this.getWidth() >= Game.WIDTH
                || newY <= 0
                || newY + this.getHeight() >= Game.HEIGHT) {
            this.stop();
            return;
        }
        this.setPosition(newX, newY);
    }

}