package Entity.EntityList;

import java.awt.*;

import Entity.Entity;
import Entity.ImageSet.BlockImageSet;
import Entity.ImageSet.ImageSet;

public class Floor extends Entity{
    private final Image image;

    public Floor(int x, int y, int width, int height) {
        super(x, y);
        ImageSet imageSet = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Floor);
        this.image = imageSet.getImage(null);
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
}
