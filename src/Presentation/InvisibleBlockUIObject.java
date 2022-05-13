package Presentation;

import java.awt.*;

import Entity.EntityList.InvisibleBlock;

public class InvisibleBlockUIObject implements UIObject {
    private InvisibleBlock invisibleBlock;

    public InvisibleBlockUIObject(InvisibleBlock invisibleBlock) {
        this.invisibleBlock = invisibleBlock;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(invisibleBlock.getX(), invisibleBlock.getY(),
                invisibleBlock.getWidth(), invisibleBlock.getHeight());
    }

}
