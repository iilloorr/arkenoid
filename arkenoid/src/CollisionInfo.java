/**
 * class: CollisionInfo.
 * <p>
 * holds information on a collision point and the object of the collision point.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collisionPoint  the collisionPoint field.
     * @param collisionObject the collisionObject field.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * method: collisionPoint.
     * accessor
     *
     * @return the collisionPoint field.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * method: collisionObject.
     * accessor
     *
     * @return the collisionObject field.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
