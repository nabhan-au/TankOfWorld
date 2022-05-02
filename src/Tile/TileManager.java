package Tile;

import javax.swing.*;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;


    public TileManager() {
        tiles = new Tile[10];
        loadImage();
    }

    private void loadImage() {
        tiles[0] = new Tile(new ImageIcon("assets/imgs/tile/Block_A_02.png").getImage(), false);
        tiles[1] = new Tile(new ImageIcon("assets/imgs/tile/Block_C_02.png").getImage(), false);
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
