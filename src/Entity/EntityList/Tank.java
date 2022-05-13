package Entity.EntityList;

import Entity.Direction;
import Entity.Entity;
import Entity.MovingEntity;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MovingEntity {
    private List<Bullet> bullets = new ArrayList<Bullet>();

    public Tank(int x, int y) {
        super(x, y, 5);
        this.setDirection(Direction.UP);
    }

    public void shoot() {
        BulletPool bulletPool = BulletPool.getInstance();
        Bullet bullet = bulletPool.borrowBullet();
        int posX = 0;
        int posY = 0;

        switch (this.getDirection()) {
            case DOWN:
                posX = this.getX() + (this.getWidth() / 2);
                posY = this.getY() + this.getHeight() + 1;
                break;
            case LEFT:
                posX = this.getX() - 1;
                posY = this.getY() + (this.getHeight() / 2);
                break;
            case RIGHT:
                posX = this.getX() + this.getHeight() + 1;
                posY = this.getY() + (this.getHeight() / 2);
                break;
            case UP:
                posX = this.getX() + (this.getHeight() / 2);
                posY = this.getY() - 1;
                break;
            default:
                posX = this.getX();
                posY = this.getY();
                break;

        }

        bullet.setPosition(posX, posY);
        bullet.setDirection(this.getDirection());
        bullet.move();
        bullets.add(bullet);
    }

    public List<Entity> isBulletHit(List<Entity> entities) {
        BulletPool bulletPool = BulletPool.getInstance();
        List<Entity> hittedEntity = new ArrayList<Entity>();

        for (int i = bullets.size() - 1; i > -1; i--) {
            for (Entity entity : entities) {
                if (bullets.get(i).isHit(entity)) {
                    entity.onHit();
                    bulletPool.returnBullet(bullets.get(i));
                    bullets.remove(i);
                    hittedEntity.add(entity);
                    break;
                }
            }
        }
        return hittedEntity;
    }

    @Override
    public void animate() {
        super.animate();
        for (Bullet bullet : bullets) {
            bullet.animate();
        }
    }

}
