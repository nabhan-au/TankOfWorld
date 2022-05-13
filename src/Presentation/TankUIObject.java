package Presentation;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;

import Entity.EntityList.Tank;
import Entity.Events.TankEvent;
import Presentation.ImageSet.TankImageSet;

public class TankUIObject extends UIObject {
    private Tank tank;
    private TankImageSet tankImageSet;
    private List<BulletUIObject> bulletUIObjects = new ArrayList<BulletUIObject>();

    public TankUIObject(Tank tank, TankImageSet tankImageSet) {
        super(tank);
        this.tank = tank;
        this.tankImageSet = tankImageSet;
    }

    @Override
    public void paint(Graphics g) {
        Image image = tankImageSet.getImage(tank.getDirection());
        g.drawImage(image, tank.getX(), tank.getY(),
                tank.getWidth(), tank.getHeight(), null);

        // Paint the Bullets.
        for (int i = bulletUIObjects.size() - 1; i > -1; i--) {
            BulletUIObject bulletUIObject = bulletUIObjects.get(i);
            if (bulletUIObject.getIsRemovable()) {
                bulletUIObjects.remove(i);
            } else {
                bulletUIObject.paint(g);
            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == TankEvent.ShootBullet.toString()) {
            bulletUIObjects.add(new BulletUIObject((Entity) event.getNewValue()));
        }

        super.propertyChange(event);
    }

}
