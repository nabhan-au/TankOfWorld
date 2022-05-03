package Tile;

import Entity.Entity;

import javax.swing.*;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;


    public TileManager() {
        tiles = new Tile[10];
        loadImage();
    }

    private void loadImage() {
        tiles[0] = new Tile(new ImageIcon("assets/imgs/tile/Ground_Tile_01_A.png").getImage(), false, false);
        tiles[1] = new Tile(new ImageIcon("assets/imgs/tile/Ground_Tile_01_C.png").getImage(), false, false);
        tiles[2] = new Tile(new ImageIcon("assets/imgs/tile/Block_A_02.png").getImage(), true, true);
        tiles[3] = new Tile(new ImageIcon("assets/imgs/tile/Block_C_02.png").getImage(), true, true);
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
