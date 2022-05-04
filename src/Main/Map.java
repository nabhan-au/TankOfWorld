package Main;

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
        generateBoundary();
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.animate();
        }

        for (Tank tank : tanks) {
            List<Entity> hitList = tank.isBulletHit(entities);
            if (hitList.size() >= 1) {
                System.out.println(hitList);
                System.out.println("X: " + hitList.get(0).getX() + " Y: " + hitList.get(0).getY());

            }

        }
    }

    public void generateBoundary() {
        for (int i = -1 * Game.BLOCK_SIZE; i < Game.BOARD_SIZE + Game.BLOCK_SIZE; i += Game.BLOCK_SIZE) {
            InvisibleBlock invisibleBlock = new InvisibleBlock(i, -1 * Game.BLOCK_SIZE);
            entities.add(invisibleBlock);
        }

        for (int i = 0; i < Game.BOARD_SIZE + Game.BLOCK_SIZE; i += Game.BLOCK_SIZE) {
            InvisibleBlock invisibleBlock = new InvisibleBlock(-1 * Game.BLOCK_SIZE, i);
            InvisibleBlock invisibleBlock2 = new InvisibleBlock(Game.BOARD_SIZE, i);
            entities.add(invisibleBlock);
            entities.add(invisibleBlock2);
        }

        for (int i = -1 * Game.BLOCK_SIZE; i < Game.BOARD_SIZE + Game.BLOCK_SIZE; i += Game.BLOCK_SIZE) {
            InvisibleBlock invisibleBlock = new InvisibleBlock(i, Game.BOARD_SIZE);
            entities.add(invisibleBlock);
        }
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
                    // entities.add(new Floor(i * tileSize, j * tileSize, tileSize, tileSize));
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
