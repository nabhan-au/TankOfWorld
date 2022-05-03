import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.*;

import javax.swing.*;

public class Map {
    private int width;
    private int height;
    private int  mapTile[][];
    private static int tileSize = 40;
    private Random random = new Random();
    private List<Tank> tanks = new ArrayList<Tank>();
    private List<Entity> entities = new ArrayList<Entity>();
    private Bullet bullet;

    public Map(int width, int height, int numPlayer) {
        this.width = width;
        this.height = height;

        // TODO: Generate the entities in the Map.
        // TODO: Remove these lines
        loadMap();

        Tank tankA = new Tank(10, 10);
        tankA.setImages(getTankImage());
        Tank tankB = new Tank(100, 100);
        tankB.setImages(getTankImage());
        tankA.setSize(tileSize, tileSize);
        tankB.setSize(tileSize, tileSize);
        bullet = new Bullet(1, 1, 0, 0);
        tanks.add(tankA);
        tanks.add(tankB);
        entities.add(tankA);
        entities.add(tankB);
        entities.add(bullet);
    }

    public List<Image> getTankImage() {
        List<Image> tanks = new ArrayList<>();
        tanks.add(new ImageIcon("assets/imgs/tank/tank_a_up.png").getImage());
        tanks.add(new ImageIcon("assets/imgs/tank/tank_a_left.png").getImage());
        tanks.add(new ImageIcon("assets/imgs/tank/tank_a_right.png").getImage());
        tanks.add(new ImageIcon("assets/imgs/tank/tank_a_down.png").getImage());
        return tanks;
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.animate();
        }

        for (int i = 0; i < tanks.size(); i++) {
            for (int j = i + 1; j < tanks.size(); j++) {
                if (tanks.get(i).isHit(tanks.get(j))) {
                    System.out.println("Hit");
                }
            }
        }

        for (Tank tank : tanks) {
            if (tank.isHit(bullet)){
                System.out.println("Hit");
            }
        }
    }

    public void loadMap() {
        mapTile = new int[height/tileSize][width/tileSize];
        try {
            File file=new File("map1.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);
            BufferedReader buffer = new BufferedReader(fr);
            for (int i = 0; i < height/tileSize; i++) {
                String line = buffer.readLine();
                for (int j = 0; j < width/tileSize; j++) {
                    String[] nums = line.split(" ");
                    int num = Integer.parseInt(nums[j]);
                    if (num == 1) {
                        entities.add(new Brick(i * tileSize, j * tileSize));
                    } else if (num == 2) {
                        entities.add(new Steel(i * tileSize, j * tileSize));
                    }
                    entities.add(new Floor(i * tileSize, j * tileSize));
                }
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
