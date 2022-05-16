package Entity;

import Main.Game;

import java.util.List;

public abstract class MovingEntity extends Entity {
    private Direction direction;
    private int speed;
    private boolean isMoving = false;
    private boolean isCollision = false;

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

    public void checkCollision(List<Entity> blocks) {
        isCollision = false;
        for (Entity block : blocks) {
            if (!block.canCollision()) {
                continue;
            }
            switch (getDirection()) {
                case UP:
                    if ((getY() <= block.getY() + block.getHeight() + speed && getY() > block.getY()) &&
                            ((getX() < block.getX() + block.getWidth() && getX() > block.getX()) ||
                                    (getX() + getWidth() > block.getX() && getX() < block.getX()) ||
                                    (getX() + getWidth() / 2 > block.getX()
                                            && getX() + getWidth() / 2 < block.getX() + block.getWidth()))) {
                        isCollision = true;
                        return;
                    }
                    break;
                case DOWN:
                    if ((getY() + getHeight() + speed >= block.getY() && getY() < block.getY()) &&
                            ((getX() < block.getX() + block.getWidth() && getX() > block.getX()) ||
                                    (getX() + getWidth() > block.getX() && getX() < block.getX()) ||
                                    (getX() + getWidth() / 2 > block.getX()
                                            && getX() + getWidth() / 2 < block.getX() + block.getWidth()))) {
                        isCollision = true;
                        return;
                    }
                    break;
                case LEFT:
                    if ((getX() <= block.getX() + block.getWidth() + speed && getX() > block.getX()) &&
                            ((getY() < block.getY() + block.getHeight() && getY() > block.getY()) ||
                                    (getY() + getHeight() > block.getY() && getY() < block.getY()) ||
                                    (getY() + getHeight() / 2 > block.getY()
                                            && getY() + getHeight() / 2 < block.getY() + block.getHeight()))) {
                        isCollision = true;
                        return;
                    }
                    break;
                case RIGHT:
                    if ((getX() + getWidth() + speed >= block.getX() && getX() < block.getX()) &&
                            ((getY() < block.getY() + block.getHeight() && getY() > block.getY()) ||
                                    (getY() + getHeight() > block.getY() && getY() < block.getY()) ||
                                    (getY() + getHeight() / 2 > block.getY()
                                            && getY() + getHeight() / 2 < block.getY() + block.getHeight()))) {
                        isCollision = true;
                        return;
                    }
                    break;
            }
        }
    }

    @Override
    public void animate() {
        if (!isMoving || isCollision) {
            return;
        }
        int newX = this.getX() + (speed * direction.getX());
        int newY = this.getY() + (speed * direction.getY());
        if (newX < -50
                || newX + this.getWidth() > Game.BOARD_SIZE + 50
                || newY < -50
                || newY + this.getHeight() > Game.BOARD_SIZE + 50) {
            this.stop();
            return;
        }
        this.setPosition(newX, newY);
    }

}