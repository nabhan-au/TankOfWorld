package Entity;

import Entity.ImageSet.ImageSet;
import Entity.ImageSet.TankImageSet;
import java.awt.*;

public class Tank extends MovingEntity {
    private ImageSet imageSet;

    public Tank(int x, int y, int tankNo) {
        super(x, y, 5);
        imageSet = TankImageSet.getTankImageSet(tankNo);
        this.setDirection(Direction.UP);
    }

    @Override
    public void paint(Graphics g) {
        Image image = imageSet.getImage(this.getDirection());
        g.drawImage(image, this.getX(), this.getY(), null);
    }

}
