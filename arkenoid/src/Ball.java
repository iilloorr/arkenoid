import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * class: ball.
 *
 * @author ilor rot
 * <p>
 * in the param section will be point values.
 * variables:
 * Point center - the center of the ball
 * int r= the radius of the ball
 * java.awt.Color color - the color of the ball
 * Velocity velocity = the velocity of the ball
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;
    private Line trajectory;
    private ArrayList<HitListener> hitListeners;

    /**
     * constructor.
     * this constructor is based on Point radius and color.
     * <p>
     * note: no constructor constructs a ball with velocity
     * setting velocity with a separate method.
     *
     * @param center       the center of the ball.
     * @param r            radius of the ball.
     * @param color        the color of the ball.
     * @param hitListeners a listeners array
     */
    public Ball(Point center, int r, java.awt.Color color, ArrayList<HitListener> hitListeners) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * constructor.
     * this constructor is based on x and y that form a Point together, radius and color.
     * <p>
     * note: no constructor constructs a ball with velocity
     * setting velocity with a separate method.
     *
     * @param x            the x coordinate of the center of the ball.
     * @param y            the y coordinate of the center of the ball.
     * @param r            radius of the ball.
     * @param hitListeners a listeners array
     * @param color        the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, ArrayList<HitListener> hitListeners) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * accessor to get the x value of the center of the ball.
     *
     * @return the x value of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor to get the y value of the center of the ball.
     *
     * @return the y value of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor to get the radius r of the ball.
     *
     * @return the radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor to get the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessor to get the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * method: setVelocity.
     * setting the velocity of the given ball
     *
     * @param v setting Velocity v as the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * method: setVelocity.
     * sets the trajectory field of the given ball.
     * the trajectory is a line based on a ball current position and its velocity,
     * for checking colliding objects on the way.
     */
    public void setTrajectory() {
        if (this.velocity == null) {
            return;
        }
        double x = this.center.getX();
        double y = this.center.getY();
        x += this.velocity.getDx();
        y += this.velocity.getDy();
        Point start = new Point(x, y);
        //under the assumption that our borders are less then 1000x1000 (for now)
        while (x < 1000 && y < 800 && x > -100 && y > -100) {
            x += this.velocity.getDx();
            y += this.velocity.getDy();
        }
        Point end = new Point(x, y);
        this.trajectory = new Line(start, end);
    }

    /**
     * method: getTrajectory.
     *
     * @return the trajectory field.
     */
    public Line getTrajectory() {
        return trajectory;
    }

    /**
     * method: setVelocity.
     * setting the velocity of the given ball.
     *
     * @param dx the velocity on the x axis that will be set to the ball.
     * @param dy the velocity on the y axis that will be set to the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * method: setGameEnvironment.
     * setting the game environment field of a ball, letting him "know"
     * of the collidables in the given environment.
     *
     * @param e the environment to be set on appropriate field
     */
    public void setGameEnvironment(GameEnvironment e) {
        this.gameEnvironment = e;
    }

    /**
     * method: moveOneStep.
     * applying a step of the given ball by its velocity value.
     * also checking for any collisions on the way, executing the
     * appropriate methods if the ball collides with an object.
     */
    public void moveOneStep() {
        CollisionInfo info = this.gameEnvironment.getClosestCollision(this.trajectory);
        Line line = new Line(trajectory.end(), info.collisionPoint());
        if (line.isOnTheLine(this.center)) {
            this.center = new Point(info.collisionPoint().getX() - this.velocity.getDx(),
                    info.collisionPoint().getY() - this.velocity.getDy());
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
            this.setTrajectory();
        } else {
            this.trajectory = new Line(this.center, this.trajectory.end());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * method: drawOn.
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface which the bal will be drawn onto.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * method: timePassed.
     * a method that is activated after certain time passes
     * by spriteCollection
     * <p>
     * executes moveOneStep periodically on time.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * method: getVelocityBySize.
     * gives a ball velocity based on his radius.
     * <p>
     * the velocity is given with speed and angle. the speed is related
     * to the ball size while the the angle is random
     *
     * @return the newly set velocity of the given ball.
     */
    public Velocity setVelocityBySize() {
        final double MAX_SPEED = 14;
        Random rand = new Random();
        double size = this.getSize();
        double angle = 360 * rand.nextDouble();
        double speed = (MAX_SPEED - size / 4);
        if (speed < 3) { //minimum speed
            speed = 3;
        }
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        return v;
    }

    /**
     * method: getVelocityBySize.
     * gives a ball velocity based on his radius.
     * <p>
     * the velocity is given with speed and angle. the speed is related
     * to the ball size while the the angle is given.
     *
     * @param angle the angle for the direction of the velocity
     * @return the newly set velocity of the given ball.
     */
    public Velocity setVelocityBySize(double angle) {
        final double MAX_SPEED = 14;
        double size = this.getSize();
        double speed = (MAX_SPEED - size / 4);
        if (speed < 3) { //minimum speed
            speed = 3;
        }
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        return v;
    }

    /**
     * method: fitBallInGUI.
     * fits the ball in the draw surface.
     * the method is simple: if the ball (including its size) are inside the draw surface
     * then the method return the ball as it is.
     * <p>
     * if the ball steps off the boundaries of the surface, the method returns a ball
     * note: this method is only for the initialization of the ball and do not check nor changes
     * its velocity. this comes in the next method.
     *
     * @param gui the base of the draw surface the ball is drawn on.
     *            used to get the width and height of the draw surface.
     * @return ball with the same radius and color as the given one, yet with
     * center point the is fitted to the surface
     */
    public Ball fitBallInGUI(GUI gui) {
        Ball tmp = this;
        if (tmp.getX() + tmp.getSize() >= gui.getDrawSurface().getWidth()) {
            tmp = new Ball(gui.getDrawSurface().getWidth() - tmp.getSize() - 1,
                    tmp.getY(), tmp.getSize(), tmp.getColor(), this.hitListeners);
        }
        if (tmp.getX() - tmp.getSize() <= 0) {
            tmp = new Ball(tmp.getSize() + 1, tmp.getY(), tmp.getSize(), tmp.getColor(), this.hitListeners);
        }
        //vertical
        if (tmp.getY() + tmp.getSize() >= gui.getDrawSurface().getHeight()) {
            tmp = new Ball(tmp.getX(), gui.getDrawSurface().getHeight() - tmp.getSize() - 1,
                    tmp.getSize(), tmp.getColor(), this.hitListeners);
        }
        if (tmp.getY() - tmp.getSize() <= 0) {
            tmp = new Ball(tmp.getX(), tmp.getSize() + 1, tmp.getSize(), tmp.getColor(), this.hitListeners);
        }
        tmp.setVelocity(this.velocity);
        return tmp;
    }

    /**
     * method: borderManagementByGui.
     * fits the ball in the draw surface.
     * <p>
     * this method changes the velocity of a given ball if it hits the borders,
     * in a way the ball will stay on the screen.
     *
     * @param gui the base of the draw surface the ball is drawn on.
     *            used to get the width and height of the draw surface.
     */
    public void borderManagementByGui(GUI gui) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        //horizontal
        if (this.getX() + this.getSize() >= gui.getDrawSurface().getWidth()) {
            dx = -dx;
        }
        if (this.getX() - this.getSize() <= 0) {
            dx = -dx;
        }
        //vertical
        if (this.getY() + this.getSize() >= gui.getDrawSurface().getHeight()) {
            dy = -dy;
        }
        if (this.getY() - this.getSize() <= 0) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
    }

    /**
     * method: fitBallInRectangle.
     * fits the ball in the given rectangle (as parameters).
     * the method is simple: if the ball (including its size) are inside the rectangle
     * then the method return the ball as it is.
     * <p>
     * if the ball steps off the boundaries of the rectangle, the method returns a ball
     * note: this method is only for the initialization of the ball and do not check nor changes
     * its velocity. this comes in the next method.
     *
     * @param x1 coordinate x  of start point of the rectangle
     * @param y1 coordinate y  of start point of the rectangle
     * @param x2 coordinate x  of end point of the rectangle
     * @param y2 coordinate y  of end point of the rectangle
     * @return ball with the same radius and color as the given one, yet with
     * center point the is fitted to the rectangle
     */
    public Ball fitBallInRectangle(double x1, double y1, double x2, double y2) {
        Ball tmp = this;
        if (tmp.getX() + tmp.getSize() >= Math.abs(x2 - x1)) {
            tmp = new Ball(Math.max(x2, x1) - tmp.getSize() - 1, tmp.getY(), tmp.getSize(), tmp.getColor(),
                    this.hitListeners);
        }
        if (tmp.getX() - tmp.getSize() <= Math.min(x1, x2)) {
            tmp = new Ball(Math.min(x1, x2) + tmp.getSize() + 1, tmp.getY(), tmp.getSize(), tmp.getColor(),
                    this.hitListeners);
        }
        //vertical
        if (tmp.getY() + tmp.getSize() >= Math.max(y2, y1)) {
            tmp = new Ball(tmp.getX(), Math.max(y2, y1) - tmp.getSize() - 1, tmp.getSize(), tmp.getColor()
                    , this.hitListeners);
        }
        if (tmp.getY() - tmp.getSize() <= Math.min(y1, y2)) {
            tmp = new Ball(tmp.getX(), Math.min(y1, y2) + tmp.getSize() + 1, tmp.getSize(), tmp.getColor(),
                    this.hitListeners);
        }
        tmp.setVelocity(this.velocity);
        return tmp;
    }

    /**
     * method: fitBallInRectangle.
     * fits the ball in the given rectangle (as parameters).
     * <p>
     * this method changes the velocity of a given ball if it hits the borders,
     * in a way the ball will stay on the rectangle.
     * <p>
     * assumes the ball is smaller then the rectangle.
     *
     * @param x1 coordinate x  of start point of the rectangle
     * @param y1 coordinate y  of start point of the rectangle
     * @param x2 coordinate x  of end point of the rectangle
     * @param y2 coordinate y  of end point of the rectangle
     */
    public void borderManagementByRectangle(double x1, double y1, double x2, double y2) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        //horizontal
        if (this.getX() + this.getSize() >= Math.max(x1, x2)) {
            dx = -dx;
        }
        if (this.getX() - this.getSize() <= Math.min(x1, x2)) {
            dx = -dx;
        }
        //vertical
        if (this.getY() + this.getSize() >= Math.max(y1, y2)) {
            dy = -dy;
        }
        if (this.getY() - this.getSize() <= Math.min(y1, y2)) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
    }

    /**
     * method: fitBallInRectangle.
     * fits the ball in the given rectangle (as parameters).
     * <p>
     * this method changes the velocity of a given ball if it hits the borders,
     * in a way the ball will stay on the rectangle.
     * <p>
     * assumes the ball is smaller then the rectangle.
     *
     * @param rect the rectangle to be managed by. (same as the previous method, gets the
     *             x1,y1,x2,y2 from the points of rect.
     */
    public void borderManagementByRectangle(Rectangle rect) {
        borderManagementByRectangle(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
    }

    /**
     * method: fitBallInRectangle.
     * fits the ball in the given rectangle (as parameters).
     * the method is simple: if the ball (including its size) are inside the rectangle
     * then the method return the ball as it is.
     * <p>
     * if the ball steps off the boundaries of the rectangle, the method returns a ball
     * note: this method is only for the initialization of the ball and do not check nor changes
     * its velocity. this comes in the next method.
     *
     * @param rect the rectangle to be managed by. (same as the previous fitBallInRectangle method, gets the
     *             x1,y1,x2,y2 from the points of rect.)
     * @return ball with the same radius and color as the given one, yet with
     * center point the is fitted to the rectangle
     */
    public Ball fitBallInRectangle(Rectangle rect) {
        return fitBallInRectangle(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
    }

    /**
     * method: addToGame.
     * adds the ball to the game by adding it to the sprite collection of the game
     *
     * @param game the game the ball is added to.
     */
    public void addToGame(GameLevel game) {
        Sprite ball = this;
        game.addSprite(ball);
    }

    /**
     * method: removeFromGame.
     * adds the ball to the game by removing it from the sprite collection of the game
     *
     * @param game the game the ball is removed from.
     */
    public void removeFromGame(GameLevel game) {
        Sprite blockSprite = this;
        game.removeSprite(blockSprite);
        this.hitListeners = null;
    }

    /**
     * method: addHitListeners.
     * adds a hitListener to the hitListeners field.
     *
     * @param h hitListener to be added.
     */
    public void addHitListeners(HitListener h) {
        this.hitListeners.add(h);
    }
}
