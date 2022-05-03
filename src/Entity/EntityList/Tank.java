package Entity.EntityList;

import Entity.Direction;
import Entity.MovingEntity;
import Entity.ImageSet.TankImageSet;
import java.awt.*;

public class Tank extends MovingEntity {
    private TankImageSet imageSet;

    public Tank(int x, int y, TankImageSet.TankImage tankNo) {
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
