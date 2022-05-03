package Entity.ImageSet;

import java.awt.*;

import Entity.Direction;

public abstract class ImageSet {
    public ImageSet(String up, String left, String right, String down) {
    }

    abstract public Image getImage(Direction direction);

}
