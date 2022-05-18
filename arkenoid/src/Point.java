/**
 * class: Point.
 * <p>
 * describes a point in a space.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * <p>
     *
     * @param x is the x value of the point (by x axis)
     * @param y is the y value of the point (by y axis)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * method: distance.
     * <p>
     * gets another point as parameter and return the distance between this point and the other.
     *
     * @param other is the second point while "this" is the first
     * @return the distance in double.
     */
    public double distance(Point other) {
        return (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));
    }

    /**
     * method: equals.
     * <p>
     * gets another point as parameter and return if it equal to "this"
     *
     * @param other is the second point while "this" is the first
     * @return the true if the same point, false otherwise.
     */
    public boolean equals(Point other) {
        return this.distance(other) == 0;
    }

    /**
     * accessor.
     *
     * @return x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * accessor.
     *
     * @return y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * method: toString
     * <p>
     * a method to simplify print of a point.
     *
     * @return a nice representation of the point
     */
    public String toString() {
        return "x = " + this.x + " y = " + this.y;
    }

}
