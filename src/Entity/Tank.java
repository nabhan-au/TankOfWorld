package Entity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Tank extends MovingEntity {
    private String direction = "up";
    private List<Image> images;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void draw(Graphics g) {
        if (direction == "up"){
            g.drawImage(images.get(0), getX(), getY(), getWidth(), getHeight(), null, null);
        } else if (direction == "left"){
            g.drawImage(images.get(1), getX(), getY(), getWidth(), getHeight(), null, null);
        } else if (direction == "right"){
            g.drawImage(images.get(2), getX(), getY(), getWidth(), getHeight(), null, null);
        } else if (direction == "down"){
            g.drawImage(images.get(3), getX(), getY(), getWidth(), getHeight(), null, null);
        }
    }

    public Tank(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        this.setMovingDirection(0, -1);
    }

    public void moveLeft() {
        this.setMovingDirection(-1, 0);
    }

    public void moveRight() {
        this.setMovingDirection(1, 0);
    }

    public void moveDown() {
        this.setMovingDirection(0, 1);
    }

    public void stopMove() {
        this.setMovingDirection(0, 0);
    }

}
