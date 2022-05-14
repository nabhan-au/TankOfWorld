package Presentation.Layers;

import javax.swing.JPanel;

import Main.Game;

import java.awt.*;

import Presentation.ImageSet.BlockImageSet;
import Presentation.ImageSet.ImageSet;

public class FloorGameLayer extends JPanel {
    private ImageSet floorImageSet = BlockImageSet.getBlockImage(BlockImageSet.BlockImage.Floor);

    public FloorGameLayer() {
        setOpaque(false);
        setBounds(0, 0, Game.BOARD_SIZE, Game.BOARD_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintFloor(g);
    }

    public void paintFloor(Graphics g) {
        for (int i = 0; i < Game.BOARD_SIZE; i += Game.BLOCK_SIZE) {
            for (int j = 0; j < Game.BOARD_SIZE; j += Game.BLOCK_SIZE) {
                g.drawImage(floorImageSet.getUp(), i, j,
                        Game.BLOCK_SIZE, Game.BLOCK_SIZE, null);
            }
        }
    }
}
