package Presentation;

import java.awt.*;

import Entity.EntityList.Tree;
import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.BlockImageSet.BlockImage;

public class TreeUIObject implements UIObject {
    private Tree tree;
    private Image image;

    public TreeUIObject(Tree tree) {
        this.tree = tree;
        this.image = BlockImageSet.getBlockImage(BlockImage.Tree).getImage(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, tree.getX(), tree.getY(),
                tree.getWidth(), tree.getHeight(), null);
    }

}
