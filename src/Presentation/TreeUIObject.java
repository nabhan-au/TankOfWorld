package Presentation;

import java.awt.*;

import Entity.Entity;
import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.BlockImageSet.BlockImage;

public class TreeUIObject extends UIObject {
    private Entity tree;
    private Image image;

    public TreeUIObject(Entity tree) {
        super(tree);
        this.tree = tree;
        this.image = BlockImageSet.getBlockImage(BlockImage.Tree).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, tree.getX(), tree.getY(),
                tree.getWidth(), tree.getHeight(), null);
    }

}
