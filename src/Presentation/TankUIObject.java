package Presentation;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Entity.EntityList.Tank;
import Entity.Events.TankEvent;
import Main.Game;
import Presentation.ImageSet.ImageSet;
import Presentation.ImageSet.TankImageSet;

public class TankUIObject extends UIObject {
    private Tank tank;
    private TankImageSet tankImageSet;
    private Game game;
    private List<UIObject> subUIObjects = new ArrayList<UIObject>();

    public TankUIObject(Tank tank, TankImageSet tankImageSet, Game game) {
        super(tank);
        this.game = game;
        this.tank = tank;
        this.tankImageSet = tankImageSet;
    }

    @Override
    public void paint(Graphics g) {
        Image image = tankImageSet.getImage(tank.getDirection());

        g.drawImage(image, tank.getX(), tank.getY(),
                tank.getWidth(), tank.getHeight(), null);

        // Paint the Bullets & Explosion Effect.
        for (int i = subUIObjects.size() - 1; i > -1; i--) {
            UIObject uiObject = subUIObjects.get(i);
            if (uiObject.getIsRemovable()) {
                subUIObjects.remove(i);
            } else {
                uiObject.paint(g);
            }
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == TankEvent.ShootBullet.toString()) {
            BulletUIObject bulletUIObject = new BulletUIObject((Entity) event.getNewValue());
            subUIObjects.add(bulletUIObject);

        } else if (event.getPropertyName() == TankEvent.HitTarget.toString()) {
            ExplosionUIObject explosionUIObject = new ExplosionUIObject((Entity) event.getNewValue());
            this.game.getEffectGameLayer().addUIObject(explosionUIObject);
        }

        super.propertyChange(event);
    }

    public Tank getTank() {
        return this.tank;
    }

    public ImageSet getTankImageSet() {
        return this.tankImageSet;
    }

}
