package Presentation.ImageSet;

import java.awt.Image;

import Entity.Direction;

public class TankImageSet extends ImageSet {
    public TankImageSet(ImageSetBuilder builder) {
        super(builder);
    }

    public static class TankImageBuilder extends ImageSetBuilder {

        @Override
        public TankImageSet build() {
            return new TankImageSet(this);
        }

    }

    @Override
    public Image getImage(Direction direction) {
        switch (direction) {
            case DOWN:
                return this.getDown();
            case LEFT:
                return this.getLeft();
            case RIGHT:
                return this.getRight();
            case UP:
                return this.getUp();
            default:
                return this.getUp();

        }

    }

    public static enum TankImage {
        A;
    }

    public static TankImageSet getTankImageSet(TankImage tankImageNo) {
        TankImageBuilder tankImageBuilder = new TankImageBuilder();
        switch (tankImageNo) {
            case A:
                tankImageBuilder
                        .up("assets/imgs/tank/tank_a_up.png")
                        .left("assets/imgs/tank/tank_a_left.png")
                        .right("assets/imgs/tank/tank_a_right.png")
                        .down("assets/imgs/tank/tank_a_down.png");
                return tankImageBuilder.build();
            default:
                throw new Error("Unknown Tank Number.");
        }
    }

}
