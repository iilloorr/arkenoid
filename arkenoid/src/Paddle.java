import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import static java.lang.System.exit;

/**
 * class: Paddle.
 * <p>
 * the paddle in our game. is also a sprite and a collidable.
 * has a keyboard sensor as a field to get keyboard calls.
 * has a rectangle field for drawing and getting the paddle borders.
 * has a color field for the color of the paddle (orange by default and currently unchangeable.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final java.awt.Color color = Color.orange;
    private Rectangle paddle;
    private int speed = 10;

    /**
     * method: Paddle.
     * constructor
     *
     * @param paddle   for the paddle field
     * @param keyboard for the keyboard field
     */
    public Paddle(Rectangle paddle, biuoop.KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.keyboard = keyboard;
    }

    /**
     * method: Paddle.
     * constructor
     *
     * @param paddle   for the paddle field
     * @param keyboard for the keyboard field
     * @param speed  the speed of the paddle
     */
    public Paddle(Rectangle paddle, biuoop.KeyboardSensor keyboard, int speed) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * method: moveLeft.
     * moving the paddle left by 10 pixels on the x axis
     */
    public void moveLeft() {
        this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - this.speed,
                this.paddle.getUpperLeft().getY()), this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * method: moveRight.
     * moving the paddle right by 10 pixels on the x axis
     */
    public void moveRight() {
        this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + this.speed,
                this.paddle.getUpperLeft().getY()), this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * method: drawOn.
     * drawing the paddle on the surface parameter
     *
     * @param d the surface the paddle is drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * method: timePassed.
     * a method that is activated after certain time passes
     * by spriteCollection
     * <p>
     * if the left keyboard is pressed during the execution of this method
     * the method moveLeft is executed.
     * if the right keyboard is pressed during the execution of this method
     * the method moveRight is executed
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            if (this.paddle.getUpperLeft().getX() < 20) {
                moveRight();
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() > 780) {
            moveLeft();
        }
    }


    /**
     * method: getCollisionRectangle.
     *
     * @return the paddle field.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * method: hit.
     * executes when the ball hit the paddle.
     * based on whe collisionPoint parameter (the point where the ball hits the paddle) and the velocity
     * of the ball, returning the new velocity of the ball.
     * <p>
     * if the ball hits the paddle in the sides the paddle will send the ball in a different direction on the
     * y axis.
     * <p>
     * if the ball hits the upper line of the paddle then the angle which the ball returning is based on where
     * it hits.
     *
     * @param collisionPoint  the point on the paddle the ball collides in.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the ball that hit the paddle.
     * @return a velocity based on where the ball hit the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //getting the 4 points of paddle.
        Point p1 = this.paddle.getUpperLeft();
        Point p2 = new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(),
                this.paddle.getUpperLeft().getY());
        Point p3 = new Point(this.paddle.getUpperLeft().getX(),
                this.paddle.getUpperLeft().getY() + this.paddle.getHeight());
        Point p4 = new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth(),
                this.paddle.getUpperLeft().getY() + this.paddle.getHeight());
        //getting 3 of the paddle lines (all but the bottom line)
        Line[] linesOfTheRectangle = new Line[3];
        //upper horizontal
        linesOfTheRectangle[0] = new Line(p1, p2);
        //left vertical
        linesOfTheRectangle[1] = new Line(p1, p3);
        //right vertical
        linesOfTheRectangle[2] = new Line(p2, p4);
        //getting 1 vertical and 1 horizontal line from the center of the
        // ball to get what is the closest collision point.
        Line vertical = new Line(collisionPoint.getX(), 0, collisionPoint.getX(), p4.getY());
        Line horizontal = new Line(0, collisionPoint.getY(), p4.getX(), collisionPoint.getY());
        Point[] intersections = new Point[3];
        //the first intersection between vertical line of the collisionPoint with the upper line of paddle
        intersections[0] = vertical.intersectionWith(linesOfTheRectangle[0]);
        //the other 2 intersections between horizontal line of collisionPoint with the left and right
        //lines of the paddle.
        for (int i = 1; i < 3; i++) {
            intersections[i] = horizontal.intersectionWith(linesOfTheRectangle[i]);
            if (horizontal.start().equals(horizontal.end()) && linesOfTheRectangle[i].isOnTheLine(horizontal.start())) {
                intersections[i] = horizontal.start();
            }
        }
        //getting the line of the paddle which is collided (by distance)
        double minimumDistance = Double.POSITIVE_INFINITY;
        Line closestLine = null;
        for (int i = 0; i < 3; i++) {
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
        //another case of the ball hits left or right of a block, an edge case.
        if (closestLine.getSlope() == Double.POSITIVE_INFINITY) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //if the ball hits the top of the paddle, then it depends where it hits
        else {
            //this loop divides the top line of the paddle into 5 sections, determines the velocity return by
            //the location of the collision point based on the sections
            Velocity v = null;
            for (int i = 0; i < 5; i++) {
                Line tmp = new Line(new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth() * (i) / 5,
                        this.paddle.getUpperLeft().getY()),
                        new Point(this.paddle.getUpperLeft().getX() + this.paddle.getWidth() * (i + 1) / 5,
                                this.paddle.getUpperLeft().getY()));
                if (tmp.isOnTheLine(collisionPoint)) {
                    double ballSpeed = currentVelocity.getSpeedByDxDy();
                    v = Velocity.fromAngleAndSpeed(300 + (i) * 30, ballSpeed);
                    break;
                }
            }
            //if we have any kind of error (which we shouldn't, this is mostly for debug)
            if (v == null) {
                System.out.println("error, something went wrong at the velocity calculation");
                exit(1);
            }
            return v;
        }
    }

    /**
     * method: addToGame.
     * adds the paddle to game (by adding it to it's sprites and collidables).
     *
     * @param g is the game the paddle added to.
     */
    public void addToGame(GameLevel g) {
        Collidable a = this;
        g.addCollidable(a);
        Sprite b = this;
        g.addSprite(this);
    }
}