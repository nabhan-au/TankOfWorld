package Presentation;

import java.awt.*;
import java.beans.PropertyChangeEvent;

import Entity.Entity;
import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.BlockImageSet.BlockImage;

public class SteelUIObject extends UIObject {
    private Image image;

    public SteelUIObject(Entity steel) {
        super(steel);
        this.image = BlockImageSet.getBlockImage(BlockImage.Steel).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.getEntity().getX(), this.getEntity().getY(),
                this.getEntity().getWidth(), this.getEntity().getHeight(), null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub

    }

}
