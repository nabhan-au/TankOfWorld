package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapData {
    private String mapName;
    private int mapSize;
    private String filePath;
    private List<int[]> elementsLocation = new ArrayList<int[]>();

    private List<int[]> freeSpaces = new ArrayList<int[]>();

    public MapData(String filePath) throws IOException {
        this.filePath = filePath;
        File mapFile = new File(filePath);
        FileReader mapFileReader;
        mapFileReader = new FileReader(mapFile);

        BufferedReader buffer = new BufferedReader(mapFileReader);
        this.mapName = buffer.readLine();
        int height = 0;
        while (true) {
            String mapData = buffer.readLine();
            if (mapData == null) {
                break;
            }
            String[] nums = mapData.split(" ");
            int[] numInInt = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int parsedMapInformation = Integer.parseInt(nums[i]);
                numInInt[i] = parsedMapInformation;
                if (parsedMapInformation == 0) {
                    int[] location = new int[2];
                    location[0] = i;
                    location[1] = height;
                    freeSpaces.add(location);
                }
            }
            height++;
            this.elementsLocation.add(numInInt);
        }

        buffer.close();
        this.mapSize = height;
        checkIfMapValid();
    }

    private void checkIfMapValid() {
        if (this.mapSize != 20) {
            throw new Error(
                    "Map Data Invalid for " + filePath + " (Map must have size 20x20)");
        }
        for (int[] widthElement : elementsLocation) {
            if (this.mapSize != widthElement.length) {
                throw new Error(
                        "Map Data Invalid for " + filePath + "(" + this.mapSize + "/" + widthElement.length + ")");
            }
        }
    }

    public String getMapName() {
        return this.mapName;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public List<int[]> getElementsLocation() {
        return this.elementsLocation;
    }

    public List<int[]> getFreeSpaces() {
        return this.freeSpaces;
    }

}