package Tile;

import Entity.Entity;

import java.awt.*;

public class Tile {
    private Image image;
    private boolean collision;
    private boolean breakable;

    public Tile(Image image, boolean collision, boolean breakable) {
        this.image = image;
        this.collision = collision;
        this.breakable = breakable;
    }

    public Image getImage() {
        return image;
    }
}
