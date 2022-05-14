package Entity.EntityList;

import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    public static int INITIAL_BULLET_SIZE = 5;
    private List<Bullet> bulletPool = new ArrayList<Bullet>();
    private List<Bullet> reloadingBulletPool = new ArrayList<Bullet>();
    private Thread reloadingThread;
    private boolean isReloading = false;

    public BulletPool() {
        for (int i = 0; i < INITIAL_BULLET_SIZE; i++) {
            bulletPool.add(new Bullet());
        }

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
                            sleep(2000);
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
        return BulletPool.INITIAL_BULLET_SIZE;
    }

    public boolean getIsRealoding() {
        return this.isReloading;
    }

}
