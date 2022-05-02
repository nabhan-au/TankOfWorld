package Tile;

import java.awt.*;

public class Tile {
    private Image image;
    private boolean collision;

    public Tile(Image image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public Image getImage() {
        return image;
    }
}
