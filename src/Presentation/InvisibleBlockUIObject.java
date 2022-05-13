package Presentation;

import java.awt.*;

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

}
