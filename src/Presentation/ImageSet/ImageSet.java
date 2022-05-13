package Presentation.ImageSet;

import java.awt.*;

import javax.swing.ImageIcon;

import Entity.Direction;

public abstract class ImageSet {
    private Image up;
    private Image down;
    private Image left;
    private Image right;

    /**
     * This is a class containing the set of image for a tank
     * 
     * @param imageSetBuilder is the builder object for creating the ImageSet
     */
    public ImageSet(ImageSetBuilder imageSetBuilder) {
        this.up = imageSetBuilder.up;
        this.down = imageSetBuilder.down;
        this.left = imageSetBuilder.left;
        this.right = imageSetBuilder.right;
    }

    abstract public Image getImage(Direction direction);

    public static abstract class ImageSetBuilder {
        private Image up;
        private Image down;
        private Image left;
        private Image right;

        /**
         * Set the Up direction image for a specific entity.
         * 
         * @param path Path the the file
         * @return ImageSetBuilder
         */
        public ImageSetBuilder up(String path) {
            this.up = new ImageIcon(path).getImage();
            return this;
        }

        /**
         * Set the Down direction image for a specific entity.
         * 
         * @param path Path the the file
         * @return ImageSetBuilder
         */
        public ImageSetBuilder down(String path) {
            this.down = new ImageIcon(path).getImage();
            return this;
        }

        /**
         * Set the Left direction image for a specific entity.
         * 
         * @param path Path the the file
         * @return ImageSetBuilder
         */
        public ImageSetBuilder left(String path) {
            this.left = new ImageIcon(path).getImage();
            return this;
        }

        /**
         * Set the Right direction image for a specific entity.
         * 
         * @param path Path the the file
         * @return ImageSetBuilder
         */
        public ImageSetBuilder right(String path) {
            this.right = new ImageIcon(path).getImage();
            return this;
        }

        abstract public ImageSet build();

    }

    public Image getUp() {
        return up;
    }

    public Image getDown() {
        return down;
    }

    public Image getLeft() {
        return left;
    }

    public Image getRight() {
        return right;
    }
}
