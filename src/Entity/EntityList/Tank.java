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
    private TankType tankType;

    public Tank(int x, int y, TankType tankType) {
        super(x, y, tankType.getSpeed());
        this.tankType = tankType;
        this.bulletPool = new BulletPool(tankType.getBulletSize());
        this.setDirection(Direction.UP);
        tankName = "Annonymous";
        setSize(Game.BLOCK_SIZE - 10, Game.BLOCK_SIZE - 10);

    }

    public Tank(int x, int y, String name, TankType tankType) {
        this(x, y, tankType);
        this.tankName = name;
    }

    @Override
    public boolean canCollision() {
        return true;
    }

    @Override
    public void onHit() {
        super.onHit();
        this.flagToBeRemove();
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
                if (entity.isHit(bullets.get(i))) {
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

    public TankType getTankType() {
        return this.tankType;
    }

    public enum TankType {
        COCKROACH(3, 3),
        MACHINE_GUNDER(1, 50),
        BROKEN_TANK(0, 10000);

        private int bulletSize;
        private int speed;

        private TankType(int speed, int bulletSize) {
            this.speed = speed;
            this.bulletSize = bulletSize;
        }

        public int getBulletSize() {
            return this.bulletSize;
        }

        public int getSpeed() {
            return this.speed;
        }

        public String getName() {
            String name = this.name();
            name = name.toLowerCase();
            String[] nameSplitted = name.split("_");
            String finalName = "";
            for (String element : nameSplitted) {
                finalName += element.substring(0, 1).toUpperCase() + element.substring(1, element.length());
            }
            return finalName;
        }
    }

}
