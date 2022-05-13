package Presentation;

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Entity.Entity;
import Presentation.ImageSet.ExplosionEffectImageSet;

public class ExplosionUIObject extends UIObject {
    private Iterator<Image> explosionEffectImages;
    private Thread delayThread;
    private Image explosionImage;
    private int x;
    private int y;
    private int width;
    private int height;

    public ExplosionUIObject(Entity oldEntity) {
        super();
        this.x = oldEntity.getX();
        this.y = oldEntity.getY();
        this.width = oldEntity.getWidth();
        this.height = oldEntity.getHeight();

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
        g.drawImage(explosionImage, x, y,
                width, height, null);

    }

}
