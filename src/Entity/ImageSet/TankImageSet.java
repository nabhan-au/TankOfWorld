package Entity.ImageSet;

import java.awt.Image;

import javax.swing.ImageIcon;

import Entity.Direction;

public class TankImageSet extends ImageSet {
    private Image up;
    private Image down;
    private Image left;
    private Image right;

    /**
     * This is a class containing the set of image for a tank
     * 
     * @param up    Path to the file for up direction.
     * @param left  Path to the file for down direction.
     * @param right Path to the file for rigth direction.
     * @param down  Path to the file for down direction.
     */
    public TankImageSet(String up, String left, String right, String down) {
        super(up, left, right, down);
        this.up = new ImageIcon(up).getImage();
        this.down = new ImageIcon(down).getImage();
        this.left = new ImageIcon(left).getImage();
        this.right = new ImageIcon(right).getImage();
    }

    @Override
    public Image getImage(Direction direction) {
        switch (direction) {
            case DOWN:
                return this.down;
            case LEFT:
                return this.left;
            case RIGHT:
                return this.right;
            case UP:
                return this.up;
            default:
                return this.up;

        }

    }

    public static TankImageSet getTankImageSet(int tankNo) {
        switch (tankNo) {
            case 0:
                return new TankImageSet("assets/imgs/tank/tank_a_up.png",
                        "assets/imgs/tank/tank_a_left.png",
                        "assets/imgs/tank/tank_a_right.png",
                        "assets/imgs/tank/tank_a_down.png");
            default:
                throw new Error("Unknown Tank Number.");
        }
    }

}
