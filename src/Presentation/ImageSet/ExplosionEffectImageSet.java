package Presentation.ImageSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;

public class ExplosionEffectImageSet {
    private static ExplosionEffectImageSet bombEffectImageSet;
    private List<Image> bombEffectImages = new ArrayList<Image>();

    private ExplosionEffectImageSet() {
        List<String> bombEffectList = List.of("A", "B", "C", "D", "E", "F", "G", "H");
        for (String bombEffectFileName : bombEffectList) {
            bombEffectImages.add(
                    new ImageIcon("assets/imgs/effects/explosion/Explosion_" + bombEffectFileName + ".png").getImage());
        }
    }

    public Iterator<Image> getBombEffectIterator() {
        return this.bombEffectImages.iterator();
    }

    public static ExplosionEffectImageSet getInstance() {
        if (bombEffectImageSet == null) {
            bombEffectImageSet = new ExplosionEffectImageSet();
        }
        return bombEffectImageSet;
    }

}
