package Entity;

public abstract class MovingEntity extends Entity {
    private int dx;
    private int dy;

    public MovingEntity(int x, int y) {
        this(x, y, 0, 0);
    }

    public MovingEntity(int x, int y, int dx, int dy) {
        super(x, y);
        this.dx = dx;
        this.dy = dy;
    }

    public void setMovingDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void animate() {
        this.setPosition(this.getX() + dx, this.getY() + dy);
    }

}