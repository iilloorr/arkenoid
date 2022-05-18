/**
 * class: Velocity.
 * <p>
 * describes a velocity (right now of a ball).
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * <p>
     *
     * @param dx is the dx value of the velocity (by x axis)
     * @param dy is the dy value of the velocity (by y axis)
     */

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * method:fromAngleAndSpeed
     * is also a kind of a constructor.
     * <p>
     * this method gets 2 params in angle and speed and return a velocity in dx dy
     * and constructs it.
     *
     * @param angle is the angle of the velocity in degrees (0 is up.)
     * @param speed is the velocity in the direction of the angle.
     * @return a new velocity (with dx and dy as fields.)
     * <p>
     * note: angle and speed are not fields, but may be in the future).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //assuming 0 degrees is up
        double dx = speed * Math.sin(angle * Math.PI / 180);
        double dy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(dx, dy);
    }
    /**
     * method:getSpeedByDxDy
     * <p>
     * calculates the speed of an object with velocity.
     * mostly for the fromAngleAndSpeed function.
     *
     * @return the speed of the object in double.
     */
    public double getSpeedByDxDy() {
        //assuming 0 degrees is up
        double speed = Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
        return speed;
    }

    /**
     * accessor.
     *
     * @return dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * accessor.
     *
     * @return dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * method:applyToPoint
     * <p>
     * this method gets a point and returns a new point with new coordinations by the velocity.
     *
     * @param p is the Point that the velocity works on.
     * @return a new Point (that is intended to replace the other point, making it move
     * by the velocity.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return (new Point(x, y));
    }

}
