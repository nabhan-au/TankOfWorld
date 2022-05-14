package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Entity.*;
import Entity.EntityList.*;
import Entity.EntityList.Tank;

public class Map {
    private int width;
    private int height;
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Entity> entities = new ArrayList<Entity>();

    public Map(int width, int height, int numPlayer, Game game) {
        this.width = width;
        this.height = height;

        loadMap("maps/map1.map");

        Tank tankA = new Tank(40, 40, "A");
        Tank tankB = new Tank(500, 500, "B");
        tankA.addPropertyChangeListener(game);
        tankB.addPropertyChangeListener(game);
        tankA.setSize(40, 40);
        tankB.setSize(40, 40);
        tanks.add(tankA);
        tanks.add(tankB);
        entities.add(tankA);
        entities.add(tankB);
        generateBoundary();
    }

    public void tick() {
        for (int i = entities.size() - 1; i > -1; i--) {
            Entity entity = entities.get(i);
            if (entity.getIsRemovable()) {
                entities.remove(i);
            } else {
                if (entity instanceof MovingEntity) {
                    ((MovingEntity) entity).checkCollision(entities);
                }
                entity.animate();
            }
        }

        for (Tank tank : tanks) {
            tank.isBulletHit(entities);
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
        try {
            File file = new File(map); // creates a new file instance
            FileReader fr = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fr);
            for (int i = 0; i < height / Game.BLOCK_SIZE; i++) {
                String line = buffer.readLine();
                String[] nums = line.split(" ");
                for (String num : nums) {
                    System.out.print(num + " ");
                }
                System.out.println("");
                for (int j = 0; j < width / Game.BLOCK_SIZE; j++) {

                    int num = Integer.parseInt(nums[j]);
                    if (num == 1) {
                        entities.add(
                                new Brick(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
                    } else if (num == 2) {
                        entities.add(
                                new Steel(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
                    } else if (num == 3) {
                        entities.add(
                                new Tree(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
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
