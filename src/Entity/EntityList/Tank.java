package Entity.EntityList;

import Entity.Direction;
import Entity.Entity;
import Entity.MovingEntity;
import Entity.Events.TankEvent;
import Main.Game;

import java.util.ArrayList;
import java.util.List;

public class Tank extends MovingEntity {
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private BulletPool bulletPool;
    private String tankName;

    public Tank(int x, int y) {
        super(x, y, 1);
        this.bulletPool = new BulletPool();
        this.setDirection(Direction.UP);
        tankName = "Annonymous";
    }

    public Tank(int x, int y, String name) {
        this(x, y);
        this.tankName = name;
    }

    @Override
    public boolean canCollision() {
        return true;
    }

    @Override
    public void onHit() {
        super.onHit();
        this.gameOver();
    }

    public void shoot() {
        Bullet bullet;
        try {
            bullet = bulletPool.borrowBullet();
        } catch (Error e) {
            if (Game.DEBUG) {
                System.out.println("DEBUG> Tank " + tankName + " ran out of bullet.");
            }
            return;
        }
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
        this.getPropertyChangeSupport().firePropertyChange(TankEvent.ShootBullet.toString(), null, bullet);
        bullet.setPosition(posX, posY);
        bullet.setDirection(this.getDirection());
        bullet.move();
        bullets.add(bullet);
        if (Game.DEBUG) {
            System.out.println("DEBUG> Tank " + tankName + " shoot bullet (Amount of Bullet: "
                    + bulletPool.getCurrentBullet() + "/" + bulletPool.getMaxBullet() + ")");
        }
    }

    public Entity isBulletHit(List<Entity> entities) {
        Entity hitEntity = null;

        for (int i = bullets.size() - 1; i > -1; i--) {
            for (Entity entity : entities) {
                if (bullets.get(i).isHit(entity)) {
                    Bullet bullet = bullets.get(i);
                    bullet.flagToBeRemove();
                    this.getPropertyChangeSupport().firePropertyChange(TankEvent.HitTarget.toString(), null, entity);
                    entity.onHit();
                    bulletPool.returnBullet(bullet);
                    bullets.remove(i);
                    hitEntity = entity;
                    break;
                }
            }
        }
        if (Game.DEBUG && hitEntity != null) {
            System.out.println(
                    "DEBUG> Tank " + tankName + "'s bullet hit " + hitEntity + " at (X: " + hitEntity.getX() + ",Y: "
                            + hitEntity.getY() + ")");
        }

        return hitEntity;
    }

    @Override
    public void animate() {
        super.animate();
        for (Bullet bullet : bullets) {
            bullet.animate();
        }
    }

    public String getTankName() {
        return this.tankName;
    }

    public BulletPool getBulletPool() {
        return this.bulletPool;
    }

    public int getCurrentBullet() {
        return this.bulletPool.getCurrentBullet();
    }

    public int getMaxBullet() {
        return this.bulletPool.getMaxBullet();
    }

    public boolean getIsRealoding() {
        return this.bulletPool.getIsRealoding();
    }

}
