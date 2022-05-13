package Presentation;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Entity.Entity;
import Entity.Events.DomainEvent;

public abstract class UIObject implements PropertyChangeListener {
    private Entity entity;
    private boolean isRemovable = false;

    public UIObject() {
    }

    public UIObject(Entity entity) {
        this.entity = entity;
        entity.addPropertyChangeListener(this);
    }

    abstract public void paint(Graphics g);

    public Entity getEntity() {
        return this.entity;
    }

    public boolean getIsRemovable() {
        return this.isRemovable;
    }

    public void flagToBeRemove() {
        this.isRemovable = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == DomainEvent.FlagAsToBeRemove.toString()) {
            this.isRemovable = true;
        }
    }
}
