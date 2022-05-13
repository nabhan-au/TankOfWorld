package Presentation;

import java.awt.*;

import Entity.EntityList.Brick;
import Presentation.ImageSet.BlockImageSet;

public class BrickUIObject implements UIObject {
    private Brick brick;
    private final Image image;

    public BrickUIObject(Brick brick) {
        this.brick = brick;
        this.image = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Brick).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, brick.getX(), brick.getY(),
                brick.getWidth(), brick.getHeight(), null);
    }

}
