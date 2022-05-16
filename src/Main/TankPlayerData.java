package Main;

import Entity.EntityList.Tank.TankType;
import Presentation.ImageSet.TankImageSet.TankImage;

public class TankPlayerData {
    private String playerName;
    private TankType tankType;
    private TankImage tankImage;

    public TankPlayerData(String playerName, TankType tankType, TankImage tankImage) {
        this.playerName = playerName;
        this.tankType = tankType;
        this.tankImage = tankImage;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public TankType getTankType() {
        return this.tankType;
    }

    public TankImage getTankImage() {
        return this.tankImage;
    }
}
