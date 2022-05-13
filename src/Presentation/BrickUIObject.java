package Presentation;

import java.awt.*;

import Entity.Entity;
import Presentation.ImageSet.BlockImageSet;

public class BrickUIObject extends UIObject {
    private final Image image;

    public BrickUIObject(Entity brick) {
        super(brick);
        this.image = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Brick).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.getEntity().getX(), this.getEntity().getY(),
                this.getEntity().getWidth(), this.getEntity().getHeight(), null);
    }

}
