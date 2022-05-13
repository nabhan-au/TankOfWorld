package Entity.EntityList;

import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    private static BulletPool bulletPoolInstance;
    private List<Bullet> bulletPool = new ArrayList<Bullet>();

    private BulletPool() {
        for (int i = 0; i < 50; i++) {
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
        this.bulletPool.add(bullet);
    }

    public static BulletPool getInstance() {
        if (bulletPoolInstance == null) {
            bulletPoolInstance = new BulletPool();
        }
        return bulletPoolInstance;
    }
}
