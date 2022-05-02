import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.*;

public class Map {
    private int width;
    private int height;
    private Random random = new Random();
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Entity> entities = new ArrayList<Entity>();

    public Map(int width, int height, int numPlayer) {
        this.width = width;
        this.height = height;

        // TODO: Generate the entities in the Map.
        // TODO: Remove these lines
        Tank tankA = new Tank(10, 10);
        Tank tankB = new Tank(100, 100);
        Bullet bullet = new Bullet(1, 1, 0, 1);
        tanks.add(tankA);
        tanks.add(tankB);
        entities.add(tankA);
        entities.add(tankB);
        entities.add(bullet);
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.animate();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Tank getTank(int tankNumber) {
        return this.tanks.get(tankNumber);
    }

}
