package Presentation.ImageSet;

import Entity.Direction;

import java.awt.*;

public class BlockImageSet extends ImageSet {
    /**
     * This is a class containing the set of image for a tank
     *
     * @param imageSetBuilder is the builder object for creating the ImageSet
     */
    public BlockImageSet(BlockImageSetBuilder imageSetBuilder) {
        super(imageSetBuilder);
    }

    public static class BlockImageSetBuilder extends ImageSetBuilder {

        @Override
        public BlockImageSet build() {
            return new BlockImageSet(this);
        }
    }

    @Override
    public Image getImage(Direction direction) {
        return this.getUp();
    }

    public static enum BlockImage {
        Floor,
        Brick,
        Steel,
        Tree;
    }

    public static BlockImageSet getBlockImage(BlockImage type) {
        BlockImageSetBuilder builder = new BlockImageSetBuilder();
        switch (type) {
            case Floor:
                builder.up("assets/imgs/tile/Ground_Tile_01_A.png");
                break;
            case Brick:
                builder.up("assets/imgs/tile/Block_A_02.png");
                break;
            case Steel:
                builder.up("assets/imgs/tile/Block_C_02.png");
                break;
            case Tree:
                builder.up("assets/imgs/tile/Block_B_02.png");
                break;
            default:
                throw new Error("Unknown block");
        }
        return builder.build();
    }
}
