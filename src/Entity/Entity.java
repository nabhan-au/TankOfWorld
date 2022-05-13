package Entity;

import Main.Game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import Entity.Events.DomainEvent;

public abstract class Entity {
    private int x;
    private int y;
    private int width = Game.BLOCK_SIZE;
    private int height = Game.BLOCK_SIZE;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private boolean isRemovable = false;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getIsRemovable() {
        return this.isRemovable;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void onHit() {
        System.out.println("Hit! Detected!");
    };

    public boolean isHit(Entity another) {
        return (getX() < another.getX() + another.getWidth()
                && getX() + getWidth() > another.getX()
                && getY() < another.getY() + another.getHeight()
                && getY() + getHeight() > another.getY());

    }

    public boolean canHit() {
        return false;
    }

    public void animate() {
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.changes;
    }

    public void flagToBeRemove() {
        this.changes.firePropertyChange(DomainEvent.FlagAsToBeRemove.toString(), false, true);
        this.isRemovable = true;
    }

    public void gameOver() {
        this.changes.firePropertyChange(DomainEvent.GameOver.toString(), false, true);
    }

}
