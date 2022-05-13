package Presentation;

import java.awt.*;
import java.beans.PropertyChangeEvent;

import Entity.Entity;

public class InvisibleBlockUIObject extends UIObject {

    public InvisibleBlockUIObject(Entity invisibleBlock) {
        super(invisibleBlock);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.getEntity().getX(), this.getEntity().getY(),
                this.getEntity().getWidth(), this.getEntity().getHeight());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub

    }

}
