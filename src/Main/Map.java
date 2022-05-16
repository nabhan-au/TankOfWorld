package Main;

import java.util.ArrayList;
import java.util.List;

import Entity.*;
import Entity.EntityList.*;
import Entity.EntityList.Tank;
import Entity.EntityList.Tank.TankType;

public class Map {
    private int width;
    private int height;
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Entity> entities = new ArrayList<Entity>();
    private MapData mapData;

    public Map(MapData mapData, int numPlayer, Game game) {
        this.width = mapData.getMapSize();
        this.height = mapData.getMapSize();

        loadMap(mapData);
        this.mapData = mapData;
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

    public void loadMap(MapData mapData) {
        List<int[]> elementLocation = mapData.getElementsLocation();

        for (int i = 0; i < elementLocation.size(); i++) {
            int[] elementInRow = elementLocation.get(i);
            for (int j = 0; j < elementInRow.length; j++) {
                if (elementInRow[j] == 1) {
                    entities.add(
                            new Brick(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
                } else if (elementInRow[j] == 2) {
                    entities.add(
                            new Steel(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
                } else if (elementInRow[j] == 3) {
                    entities.add(
                            new Tree(j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE));
                }
            }
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

    public List<Tank> getTankList() {
        return this.tanks;
    }

    public Tank createNewPlayer(String name, Game game, TankType tankType) {
        int[] freeSpace = mapData.getRandomedFreeSpaces();
        Tank newTank = new Tank(freeSpace[0] * Game.BLOCK_SIZE,
                freeSpace[1] * Game.BLOCK_SIZE, name, tankType);
        newTank.addPropertyChangeListener(game);
        this.tanks.add(newTank);
        this.entities.add(newTank);

        return newTank;
    }

}
