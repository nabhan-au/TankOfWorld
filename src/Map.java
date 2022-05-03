import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.*;
import Entity.EntityList.*;
import Entity.EntityList.Tank;
import Entity.ImageSet.TankImageSet.TankImage;

public class Map {
    private int width;
    private int height;
    private int mapTile[][];
    private static int tileSize = 40;
    private Random random = new Random();
    private List<Tank> tanks = new ArrayList<Tank>();
    // This is for the GUI Object to render each entity easily.
    private List<Entity> entities = new ArrayList<Entity>();

    public Map(int width, int height, int numPlayer) {
        this.width = width;
        this.height = height;

        // TODO: Generate the entities in the Map.
        // TODO: Remove these lines
        loadMap("map1.txt");

        Tank tankA = new Tank(40, 40, TankImage.A);
        Tank tankB = new Tank(40, 40, TankImage.A);
        tankA.setSize(40, 40);
        tankB.setSize(40, 40);
        tanks.add(tankA);
        tanks.add(tankB);
        entities.add(tankA);
        entities.add(tankB);
        System.out.println(entities);
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.animate();
        }

        // for (int i = 0; i < tanks.size(); i++) {
        // for (int j = i + 1; j < tanks.size(); j++) {
        // if (tanks.get(i).isHit(tanks.get(j))) {
        // System.out.println("Hit");
        // }
        // }
        // }

        // for (Entity entity : entities) {
        // for (Entity entity2 : entities) {
        // if (entity.isHit(entity2) && entity != entity2) {
        // System.out.println("Hit");
        // }
        // }
        // }
    }

    public void loadMap(String map) {
        mapTile = new int[height / tileSize][width / tileSize];
        try {
            File file = new File(map); // creates a new file instance
            FileReader fr = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fr);
            for (int i = 0; i < height / tileSize; i++) {
                String line = buffer.readLine();
                for (int j = 0; j < width / tileSize; j++) {
                    String[] nums = line.split(" ");
                    int num = Integer.parseInt(nums[j]);
                    entities.add(new Floor(i * tileSize, j * tileSize, tileSize, tileSize));
                    if (num == 1) {
                        entities.add(new Brick(i * tileSize, j * tileSize, tileSize, tileSize));
                    } else if (num == 2) {
                        entities.add(new Steel(i * tileSize, j * tileSize, tileSize, tileSize));
                    }
                }
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public int[][] getMapTile() {
        return mapTile;
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
