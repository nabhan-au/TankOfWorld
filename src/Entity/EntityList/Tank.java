package Entity.EntityList;

import Entity.Direction;
import Entity.Entity;
import Entity.MovingEntity;
import Entity.ImageSet.TankImageSet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MovingEntity {
    private TankImageSet imageSet;
    private List<Bullet> bullets = new ArrayList<Bullet>();

    public Tank(int x, int y, TankImageSet.TankImage tankNo) {
        super(x, y, 5);
        imageSet = TankImageSet.getTankImageSet(tankNo);
        this.setDirection(Direction.UP);
    }

    public void shoot() {
        BulletPool bulletPool = BulletPool.getInstance();
        Bullet bullet = bulletPool.borrowBullet();
        bullet.setPosition(this.getX() + (this.getWidth() / 2), this.getY() + (this.getHeight() / 2));
        bullet.setDirection(this.getDirection());
        bullet.move();
        bullets.add(bullet);
    }

    // public boolean isBulletHit(Entity entity) {
    // for (Bullet bullet: bullets) {

    // }
    // }

    @Override
    public void animate() {
        super.animate();
        for (Bullet bullet : bullets) {
            bullet.animate();
        }
    }

    @Override
    public void paint(Graphics g) {
        Image image = imageSet.getImage(this.getDirection());
        g.drawImage(image, this.getX(), this.getY(), 40, 40, null);

        for (Bullet bullet : bullets) {
            bullet.paint(g);
        }
    }

}
