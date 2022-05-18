import biuoop.DrawSurface;
/**
 * interface: Sprite.
 * <p>
 * sets the following methods to all colidables:
 * timePassed
 * drawOn
 */
public interface Sprite {
    /**
     * method: drawOn.
     * drawing the object on the surface
     *
     * @param d the surface the object is drawn on.
     */
    void drawOn(DrawSurface d);
    /**
     * method: timePassed.
     * notify the sprite that time has passed
     */
    void timePassed();

}
