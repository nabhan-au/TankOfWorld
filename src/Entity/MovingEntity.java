package Entity;

public abstract class MovingEntity extends Entity {
    private int dx;
    private int dy;

    public MovingEntity(int x, int y) {
        super(x, y);
    }

    @Override
    public void animate() {
        this.setPosition(this.getX() + dx, this.getY() + dy);
    }

}