package Presentation;

import java.awt.*;

import Entity.EntityList.Tank;
import Presentation.ImageSet.TankImageSet;

public class TankUIObject implements UIObject {
    private Tank tank;
    private TankImageSet tankImageSet;

    public TankUIObject(Tank tank, TankImageSet tankImageSet) {
        this.tank = tank;
        this.tankImageSet = tankImageSet;
    }

    @Override
    public void paint(Graphics g) {
        Image image = tankImageSet.getImage(tank.getDirection());
        g.drawImage(image, tank.getX(), tank.getY(),
                tank.getWidth(), tank.getHeight(), null);
    }

}
