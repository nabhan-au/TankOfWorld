package Presentation;

import java.awt.Graphics;

import Entity.EntityList.Tank;

public class TankInfoUIObject extends UIObject {
    private TankUIObject tankUIObject;
    private int size;
    private int x;
    private int y;

    public TankInfoUIObject(TankUIObject tankUIObject, int x, int y, int size) {
        super();
        this.tankUIObject = tankUIObject;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(tankUIObject.getTankImageSet().getUp(), x, y, size, size, null);
        Tank tank = tankUIObject.getTank();
        String stringToDraw = "Name: " + tank.getTankName() + "\n" +
                "Ammo: " + tank.getCurrentBullet() + "/" + tank.getMaxBullet();
        if (tank.getIsRealoding()) {
            stringToDraw += " (Reloading . . .)";
        }
        drawString(g, stringToDraw, x + size + 5, y);
    }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
