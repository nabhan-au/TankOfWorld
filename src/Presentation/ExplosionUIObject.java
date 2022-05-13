package Presentation;

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Entity.Entity;
import Presentation.ImageSet.ExplosionEffectImageSet;

public class ExplosionUIObject extends UIObject {
    private Iterator<Image> explosionEffectImages;
    private Entity oldEntity;
    private Thread delayThread;
    private Image explosionImage;

    public ExplosionUIObject(Entity oldEntity) {
        super();
        this.oldEntity = oldEntity;
        ExplosionEffectImageSet explosionEffectImageSet = ExplosionEffectImageSet.getInstance();
        explosionEffectImages = explosionEffectImageSet.getBombEffectIterator();
        delayThread = new Thread() {
            public void run() {
                while (!getIsRemovable()) {
                    try {
                        explosionImage = explosionEffectImages.next();
                    } catch (NoSuchElementException e) {
                        flagToBeRemove();
                    }

                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        delayThread.start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(explosionImage, oldEntity.getX(), oldEntity.getY(),
                oldEntity.getWidth(), oldEntity.getHeight(), null);

    }

}
