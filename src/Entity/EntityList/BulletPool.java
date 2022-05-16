package Entity.EntityList;

import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    public static int RELOAD_SPEED = 500;
    private List<Bullet> bulletPool = new ArrayList<Bullet>();
    private List<Bullet> reloadingBulletPool = new ArrayList<Bullet>();
    private Thread reloadingThread;
    private boolean isReloading = false;
    private int bulletSize;

    public BulletPool(int bulletSize) {
        for (int i = 0; i < bulletSize; i++) {
            bulletPool.add(new Bullet());
        }
        this.bulletSize = bulletSize;
    }

    public Bullet borrowBullet() {
        if (bulletPool.size() <= 0) {
            throw new Error("Not enough bullet");
        }
        return bulletPool.remove(0);
    }

    public void returnBullet(Bullet bullet) {
        bullet.stop();
        bullet.setPosition(-50, -50);
        this.reloadingBulletPool.add(bullet);
        if (!isReloading) {
            isReloading = true;
            this.reloadingThread = new Thread() {
                public void run() {
                    while (isReloading) {
                        try {
                            sleep(RELOAD_SPEED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (reloadingBulletPool.size() > 0) {
                            Bullet bullet = reloadingBulletPool.remove(0);
                            bulletPool.add(bullet);
                        }
                        if (reloadingBulletPool.size() == 0) {
                            // Done Reloading
                            isReloading = false;
                        }
                    }
                }
            };
            this.reloadingThread.start();
        }

    }

    public int getCurrentBullet() {
        return this.bulletPool.size();
    }

    public int getMaxBullet() {
        return this.bulletSize;
    }

    public boolean getIsRealoding() {
        return this.isReloading;
    }

}
