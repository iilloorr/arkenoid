import biuoop.DrawSurface;

/**
 * interface: Collidable.
 * <p>
 * sets the following methods to all colidables:
 * getCollisionRectangle
 * hit
 * drawOn
 */
public interface Collidable {
    /**
     * method: getCollisionRectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * method: hit.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity of the ball before colliding.
     * @param hitter the ball that hits the collidable.
     * @return velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * method: drawOn.
     * drawing the object on the surface
     *
     * @param surface the surface the object is drawn on.
     */
    void drawOn(DrawSurface surface);
}
