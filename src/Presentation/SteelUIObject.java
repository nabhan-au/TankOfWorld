package Presentation;

import java.awt.*;

import Entity.EntityList.Steel;
import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.BlockImageSet.BlockImage;

public class SteelUIObject implements UIObject {
    private Steel steel;
    private Image image;

    public SteelUIObject(Steel steel) {
        this.steel = steel;
        this.image = BlockImageSet.getBlockImage(BlockImage.Steel).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, steel.getX(), steel.getY(), steel.getWidth(), steel.getHeight(), null);
    }

}
