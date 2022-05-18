import java.awt.Color;

import biuoop.DrawSurface;

/**
 * class: Line.
 * <p>
 * describes a line in a space.
 * described though 2 Points. every other fields are determined by those 2 points.
 */
public class Line {
    private Point start;
    private Point end;
    //slope and n are for equation form: y= slope*x + n and are calculated respectively
    private double slope;
    private double n;

    /**
     * constructor.
     * <p>
     *
     * @param start first point that will be in the start field of the line
     * @param end   second point that will be in the end field of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = calculateSlope();
        this.n = calculateN();
    }

    /**
     * constructor.
     * <p>
     *
     * @param x1 the x value of start point field.
     * @param y1 the y value of start point field.
     * @param x2 the x value of end point field.
     * @param y2 the y value of end point field.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = calculateSlope();
        this.n = calculateN();
    }

    /**
     * method: calculateSlope.
     * <p>
     * a method to be used by constructor.
     * calculates the slope of the line
     * if slope does not exist returns positive infinity
     *
     * @return the slope in double.
     * <p>
     * note: if slope does not exists return positive infinity.
     */
    private double calculateSlope() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * method: calculateN.
     * <p>
     * a method to be used by constructor.
     * calculates the N value of the line (like in linear equation:
     * y = slope * x + N)
     * if slope does not exist returns negative infinity
     *
     * @return the N value of a line (like in linear equation:
     * y = slope * x + N)
     * <p>
     * note: if slope does not exists return negative infinity.
     */
    private double calculateN() {
        return this.start.getY() - this.slope * this.start().getX();
    }

    /**
     * method: length.
     * <p>
     * calculates the length between the start field and the end field of the line.
     *
     * @return the length between the start field and the end field of the line.
     * <p>
     * note: if slope does not exists return negative infinity.
     */
    public double length() {
        //returning the length by distance between start and end
        return this.start.distance(this.end);
    }

    /**
     * method: middle.
     * <p>
     * calculates the point in the middle of the line and returning it as a Point.
     *
     * @return the point in the middle of the line.
     */
    public Point middle() {
        double x = this.start.getX() / 2 + this.end.getX() / 2;
        double y = this.start.getY() / 2 + this.end.getY() / 2;
        return new Point(x, y);
    }

    /**
     * method: start.
     * <p>
     * accessor
     *
     * @return the start field.
     */
    public Point start() {
        return this.start;
    }

    /**
     * method: end.
     * <p>
     * accessor
     *
     * @return the end field.
     */
    public Point end() {
        return this.end;
    }

    /**
     * method: getSlope.
     * <p>
     * accessor
     *
     * @return the slope field.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * method: isOnTheLine.
     * <p>
     * checks if a point is on the given line. (with the linear equation y = slope * x +N)
     *
     * @param p the point that is checked.
     * @return true if the point is on the line. false otherwise
     */

    public boolean isOnTheLine(Point p) {
        if (this.slope == Double.POSITIVE_INFINITY) {
            return ((this.start.getY() <= p.getY() && p.getY() <= this.end.getY())
                    || (this.end.getY() <= p.getY() && p.getY() <= this.start.getY()));
        }
        return ((this.start.getX() - 0.0001 <= p.getX() && p.getX() <= this.end.getX() + 0.0001
                || (this.end.getX() - 0.0001 <= p.getX() && p.getX() <= this.start.getX() + 0.0001)));
    }

    /**
     * method: isIntersecting.
     * <p>
     * checks if a line is intersecting with the given line. (with the linear equations y = slope * x +N)
     *
     * @param other the other line that is checked.
     * @return true if the lines intersecting. false otherwise.
     * <p>
     * note: lines that has the same slope will always return false on that method,
     * even if they are the same line.
     * that is because of the definition of intersection (meeting at one point ONLY).
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * method: isIntersecting.
     * <p>
     * checks if a line is intersecting with the given line. (with the linear equations y = slope * x +N)
     * and returns the point of intersection if does. returns null otherwise.
     *
     * @param other the other line that is checked.
     * @return the Point of intersection as Point. null if not intersecting.
     * <p>
     * note: lines that has the same slope will always return null on that method,
     * even if they are the same line.
     * that is because of the definition of intersection (meeting at one point ONLY).
     */
    public Point intersectionWith(Line other) {
        if (this.slope == other.slope) {
            return null;
        }
        /*this condition executes if the lines arent parallel.
        -in this condition the method will find the hypothetical intersection
        -then will check if the intersection is on both the lines.
        -this condition also means that the n field of each line exists and not infinity*/
        if (this.slope != Double.POSITIVE_INFINITY && other.slope != Double.POSITIVE_INFINITY) {
            double x = (other.n - this.n) / (this.slope - other.slope);
            double y = this.slope * x + this.n;
            Point p = new Point(x, y);
            if (this.isOnTheLine(p) && other.isOnTheLine(p)) {
                return p;
            }
            return null;
        }
        if (this.slope == Double.POSITIVE_INFINITY) {
            double y = other.slope * this.start.getX() + other.n;
            Point p = new Point(this.start.getX(), y);
            if (this.isOnTheLine(p) && other.isOnTheLine(p)) {
                return p;
            }
            return null;
        }
        //if other.slope is positive infinity
        double y = this.slope * other.start.getX() + this.n;
        Point p = new Point(other.start.getX(), y);
        if (this.isOnTheLine(p) && other.isOnTheLine(p)) {
            return p;
        }
        return null;
    }

    /**
     * method equals:
     * checks if 2 lines are the same.
     * taking the original line (this) coordinates and checks if the
     * coordinates are the same with the given line (other)
     * the method checks also if the start of 1 line is the end of the other
     * and vice versa
     *
     * @param other the other line to check with.
     * @return true if the lines are the same. false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start)));
    }

    /**
     * method closestIntersectionToStartOfLine:
     * gets the closest intersection of a line and a rectangle.
     * "closest" means to the start point of the line.
     *
     * @param rect the rectangle.
     * @return point of the closest intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        final int RECTANGLE_LINES = 4;
        java.util.List<Point> points = rect.intersectionPoints(this);
        Point p = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i) != null && (p == null || this.start.distance(p) > this.start.distance(points.get(i)))) {
                p = points.get(i);
            }
        }
        return p;
    }

    /**
     * method drawOn.
     * drawing the line on a drawSurface.
     *
     * @param d the drawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) this.start().getX(), (int) this.start().getY(),
                (int) this.end().getX(), (int) this.end().getY());
    }
}
