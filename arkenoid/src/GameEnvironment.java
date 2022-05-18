import java.util.ArrayList;

/**
 * class: GameEnvironment.
 * <p>
 * stores the colliadbles of the game.
 * has methods for collidables.
 */
public class GameEnvironment {
    private java.util.List<Collidable> listOfCollidables = new ArrayList<Collidable>();

    /**
     * method: addCollidable.
     * add the given collidable to the environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        listOfCollidables.add(c);
    }

    /**
     * method: removeCollidable.
     * removes a collidable from the field listOfCollidables.
     * if the given collidable is the last in the array, it just removes him.
     * otherwise the method "fills" the hole in the array by transferring
     * the last sprite to the index of the removed collidable.
     *
     * @param c the sprite that is removed.
     */
    public void removeCollidable(Collidable c) {
        listOfCollidables.remove(c);
    }

    /**
     * method: getListOfCollidable.
     *
     * @return the listOfCollidables field.
     */
    public java.util.List getListOfCollidable() {
        return this.listOfCollidables;
    }

    /**
     * method: getClosestCollision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line that "searches" for collidables.
     * @return the closest collision point of the trajectory parameter and the collidable
     * that it collides with..
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point p;
        Point[] intersectionPoints = new Point[listOfCollidables.size()];
        for (int i = 0; i < listOfCollidables.size(); i++) {
            p = trajectory.closestIntersectionToStartOfLine(listOfCollidables.get(i).getCollisionRectangle());
            intersectionPoints[i] = p;
        }
        double minDistance = Double.POSITIVE_INFINITY;
        int indexOfCollidable = 0;
        for (int i = 0; i < listOfCollidables.size(); i++) {
            if (intersectionPoints[i] == null) {
                continue;
            }
            double distance = intersectionPoints[i].distance(trajectory.start());
            if (distance < minDistance) {
                minDistance = distance;
                indexOfCollidable = i;
            }
        }
        if (intersectionPoints[indexOfCollidable] == null) {
            System.out.println("error in getClosestCollision");
            return null;
        }
        return new CollisionInfo(intersectionPoints[indexOfCollidable], listOfCollidables.get(indexOfCollidable));
    }
}