import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * class: SpriteCollection.
 * <p>
 * has a collection of sprites, with methods to apply to all of them
 */
public class SpriteCollection {
    private java.util.List<Sprite> listOfSprites = new ArrayList<Sprite>();

    /**
     * method: addSprite.
     * adds a sprite to the field listOfSprites
     *
     * @param s the sprite that is added.
     */
    public void addSprite(Sprite s) {
        listOfSprites.add(s);
    }

    /**
     * method: removeSprite.
     * removes a sprite from the field listOfSprites.
     * if the given sprite is the last in the array, it just removes him.
     * otherwise the method "fills" the hole in the array by transferring
     * the last sprite to the index of the removed sprite.
     *
     * @param s the sprite that is removed.
     */
    public void removeSprite(Sprite s) {
        listOfSprites.remove(s);
    }

    /**
     * method: getListOfSprites.
     *
     * @return the listOfSprites field.
     */
    public List<Sprite> getListOfSprites() {
        return listOfSprites;
    }

    /**
     * method: notifyAllTimePassed.
     * use the sprite method timePassed on all the sprites in sprites collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.listOfSprites);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * method: drawAllOn.
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface which the sprites are drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.listOfSprites);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}