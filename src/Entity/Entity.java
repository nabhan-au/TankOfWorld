public abstract class Entity {
    private int x;
    private int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHit(Entity another) {
        return another.getX() == x && another.getY() == y;
    }

    public void animate() {

    }
}
