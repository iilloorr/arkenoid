import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * class: Block.
 * <p>
 * the blocks in our game. is also a sprite and a collidable.
 * has a rectangle field for drawing and getting the block borders.
 * has a color field for the color of the block (green by default).
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle brick;
    private java.awt.Color color = Color.green;
    private ArrayList<HitListener> hitListeners;
    private ScoreTrackingListener scoreListener;

    /**
     * constructor.
     * <p>
     *
     * @param brick        the brick field
     * @param hitListeners the hitListeners field.
     */
    public Block(Rectangle brick, ArrayList<HitListener> hitListeners) {
        this.brick = brick;
        this.hitListeners = hitListeners;
    }

    /**
     * constructor.
     * <p>
     *
     * @param brick         the brick field
     * @param hitListeners  the hitListeners field.
     * @param scoreListener the scoreListener field.
     */
    public Block(Rectangle brick, ArrayList<HitListener> hitListeners, ScoreTrackingListener scoreListener) {
        this.brick = brick;
        this.hitListeners = hitListeners;
        this.scoreListener = scoreListener;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft     the brick's upperPoint field (brick is a rectangle)
     * @param height        the brick's height field
     * @param width         the brick's width field
     * @param hitListeners  the hitListeners field.
     * @param scoreListener the scoreListener field.
     */
    public Block(Point upperLeft, double width, double height,
                 ArrayList<HitListener> hitListeners, ScoreTrackingListener scoreListener) {
        this.brick = new Rectangle(upperLeft, width, height);
        this.hitListeners = hitListeners;
        this.scoreListener = scoreListener;
    }

    /**
     * constructor.
     * <p>
     *
     * @param brick         the brick field
     * @param color         the color field of the block
     * @param hitListeners  the hitListeners field.
     * @param scoreListener the scoreListener field.
     */
    public Block(Rectangle brick, java.awt.Color color,
                 ArrayList<HitListener> hitListeners, ScoreTrackingListener scoreListener) {
        this.brick = brick;
        this.color = color;
        this.hitListeners = hitListeners;
        this.scoreListener = scoreListener;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft the brick's upperPoint field (brick is a rectangle)
     * @param height    the brick's height field
     * @param width     the brick's width field
     * @param color     the color field of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.brick = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft     the brick's upperPoint field (brick is a rectangle)
     * @param height        the brick's height field
     * @param width         the brick's width field
     * @param color         the color field of the block
     * @param scoreListener the scoreListener field.
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color, ScoreTrackingListener scoreListener) {
        this.brick = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.scoreListener = scoreListener;
    }

    /**
     * constructor.
     * <p>
     *
     * @param brick the brick field
     */
    public Block(Rectangle brick) {
        this.brick = brick;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft the brick's upperPoint field (brick is a rectangle)
     * @param height    the brick's height field
     * @param width     the brick's width field
     */
    public Block(Point upperLeft, double width, double height) {
        this.brick = new Rectangle(upperLeft, width, height);
    }

    /**
     * constructor.
     * <p>
     *
     * @param brick the brick field
     * @param color the color field of the block
     */
    public Block(Rectangle brick, java.awt.Color color) {
        this.brick = brick;
        this.color = color;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft    the brick's upperPoint field (brick is a rectangle)
     * @param height       the brick's height field
     * @param width        the brick's width field
     * @param color        the color field of the block
     * @param hitListeners the hitListeners field.
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color, ArrayList<HitListener> hitListeners) {
        this.brick = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * constructor.
     * <p>
     *
     * @param upperLeft     the brick's upperPoint field (brick is a rectangle)
     * @param height        the brick's height field
     * @param width         the brick's width field
     * @param color         the color field of the block
     * @param hitListeners  the hitListeners field.
     * @param scoreListener the scoreListener field.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color,
                 ArrayList<HitListener> hitListeners, ScoreTrackingListener scoreListener) {
        this.brick = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = hitListeners;
        this.scoreListener = scoreListener;
    }

    /**
     * method: drawOn.
     * draws the block on the surface parameter
     * also draws the borders of the block at black color.
     *
     * @param surface the surface the block is drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.brick.getUpperLeft().getX(), (int) this.brick.getUpperLeft().getY(),
                (int) this.brick.getWidth(), (int) this.brick.getHeight());
        surface.setColor(Color.BLACK);
        Point p1 = this.brick.getUpperLeft();
        Point p2 = new Point(this.brick.getUpperLeft().getX() + this.brick.getWidth(),
                this.brick.getUpperLeft().getY());
        Point p3 = new Point(this.brick.getUpperLeft().getX(),
                this.brick.getUpperLeft().getY() + this.brick.getHeight());
        Point p4 = new Point(this.brick.getUpperLeft().getX() + this.brick.getWidth(),
                this.brick.getUpperLeft().getY() + this.brick.getHeight());
        surface.drawLine((int) p1.getX(), (int) p1.getY(),
                (int) p2.getX(), (int) p2.getY());
        surface.drawLine((int) p1.getX(), (int) p1.getY(),
                (int) p3.getX(), (int) p3.getY());
        surface.drawLine((int) p3.getX(), (int) p3.getY(),
                (int) p4.getX(), (int) p4.getY());
        surface.drawLine((int) p2.getX(), (int) p2.getY(),
                (int) p4.getX(), (int) p4.getY());
    }

    /**
     * method: timePassed.
     * a method that is activated after certain time passes
     * does nothing on a block for now, but has to be defined for
     * the sprite interface.
     */
    @Override
    public void timePassed() {

    }

    /**
     * method: getCollisionRectangle.
     * a method that belongs to the interface Collidable.
     *
     * @return the collision rectangle which is brick
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.brick;
    }

    /**
     * method: hit.
     * executes when the ball hit the block.
     * based on whe collisionPoint parameter (the point where the ball hits the block) and the velocity
     * of the ball, returning the new velocity of the ball.
     * <p>
     * if the ball hits the block in the sides the block will send the ball in a different direction on the
     * y axis.
     * <p>
     * if the ball hits the block in the sides the block will send the ball in a different direction on the
     * x axis.
     *
     * @param collisionPoint  the point on the block the ball collides in.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the ball the hits the block
     * @return a velocity based on where the ball hit the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.hitListeners != null) {
            this.notifyHit(hitter);
        }
        //getting the 4 points of brick.
        Point p1 = this.brick.getUpperLeft();
        Point p2 = new Point(this.brick.getUpperLeft().getX() + this.brick.getWidth(),
                this.brick.getUpperLeft().getY());
        Point p3 = new Point(this.brick.getUpperLeft().getX(),
                this.brick.getUpperLeft().getY() + this.brick.getHeight());
        Point p4 = new Point(this.brick.getUpperLeft().getX() + this.brick.getWidth(),
                this.brick.getUpperLeft().getY() + this.brick.getHeight());
        //then by the 4 points get the 4 lines of brick.
        Line[] linesOfTheRectangle = new Line[4];
        //upper horizontal
        linesOfTheRectangle[0] = new Line(p1, p2);
        //lower horizontal
        linesOfTheRectangle[1] = new Line(p3, p4);
        //left vertical
        linesOfTheRectangle[2] = new Line(p1, p3);
        //right vertical
        linesOfTheRectangle[3] = new Line(p2, p4);
        //getting 1 vertical and 1 horizontal line from the center of the
        // ball to get what is the closest collision point.
        Line vertical = new Line(collisionPoint.getX(), 0, collisionPoint.getX(), p4.getY());
        Line horizontal = new Line(0, collisionPoint.getY(), p4.getX(), collisionPoint.getY());
        //getting the intersection points of the horizontal and vertical lines with the block.
        Point[] intersections = new Point[4];
        for (int i = 0; i < 2; i++) {
            intersections[i] = vertical.intersectionWith(linesOfTheRectangle[i]);
            intersections[i + 2] = horizontal.intersectionWith(linesOfTheRectangle[i + 2]);
            //solving a specific problem with scopes are infinity if line is a point
            if (horizontal.start().equals(horizontal.end())
                    && linesOfTheRectangle[i + 2].isOnTheLine(horizontal.start())) {
                intersections[i + 2] = horizontal.start();
            }
        }
        //getting the line of the block which is collided (by distance)
        double minimumDistance = Double.POSITIVE_INFINITY;
        Line closestLine = null;
        for (int i = 0; i < 4; i++) {
            if (intersections[i] == null) {
                continue;
            }
            if (minimumDistance > collisionPoint.distance(intersections[i])) {
                minimumDistance = collisionPoint.distance(intersections[i]);
                closestLine = linesOfTheRectangle[i];
            }
        }
        //if we have any kind of error (which we shouldn't, this is mostly for debug)
        if (closestLine == null) {
            System.out.println("error! function:hit class: Block : collisionPoint provided is wrong"
                    + "objects given do not collide at the given Point.");
            exit(1);
        }
        //if the ball hits left or right of a block, change dy of velocity
        if (collisionPoint.equals(p1) || collisionPoint.equals(p2) || collisionPoint.equals(p3)
                || closestLine.equals(p4)) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //another case of the ball hits left or right of a block, an edge case.
        if (closestLine.getSlope() == 0) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        //if the ball hits top of bottom of a block, change dx of velocity
        else {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * method: addToGame.
     * adds the block to game (by adding it to it's sprites and collidables).
     *
     * @param game is the game the block added to.
     */
    public void addToGame(GameLevel game) {
        Sprite blockSprite = this;
        game.addSprite(blockSprite);
        Collidable blockCollidable = this;
        game.addCollidable(blockCollidable);
    }

    /**
     * method: addToGame.
     * removes the block to game (by removing it to it's sprites and collidables).
     *
     * @param game is the game the block is removed from.
     */
    public void removeFromGame(GameLevel game) {
        Sprite blockSprite = this;
        game.removeSprite(blockSprite);
        Collidable blockCollidable = this;
        game.removeCollidable(blockCollidable);
        this.hitListeners = null;
    }

    /**
     * method: addHitListener.
     * adds a HitListener to the field hitListeners
     *
     * @param hl the HitListener that is added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * method: removeHitListener.
     * removes a HitListener from the field hitListeners.
     * if the given HitListener is the last in the array, it just removes him.
     * otherwise the method "fills" the hole in the array by transferring
     * the last HitListener to the index of the removed HitListener.
     *
     * @param hl the HitListener that is removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        int tmp = hitListeners.indexOf(hl);
        int tmp2 = hitListeners.size() - 1;
        hitListeners.remove(hl);
        //if the removed HitListener is the last one on the list, we return
        if (tmp == tmp2) {
            return;
        }
        //otherwise, we take the last HitListener and replace its index with the removed HitListener's index
        //the reason for it is we don't want holes in our array list.
        hitListeners.add(tmp, hitListeners.get(tmp2));
        hitListeners.remove(hitListeners.get(tmp2));
    }
    /**
     * method: notifyHit.
     * notifies all listeners that the block has been hit.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
