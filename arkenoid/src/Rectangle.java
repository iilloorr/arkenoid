
/**
 * class: Rectangle.
 * <p>
 * a rectangle in the game.
 * built by an upperLeft point , width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor.
     *
     * @param upperLeft the upperleft field
     * @param width     the width field
     * @param height    the height field
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * method: intersectionPoints.
     * return the intersection points of a line with the rectangle
     *
     * @param line the specified line (look @return).
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> points = new java.util.ArrayList<Point>();
        //getting the points of the Rectangle
        Point p1 = upperLeft;
        Point p2 = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point p3 = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point p4 = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Line[] linesOfTheRectangle = new Line[4];
        linesOfTheRectangle[0] = new Line(p1, p2);
        linesOfTheRectangle[1] = new Line(p1, p3);
        linesOfTheRectangle[2] = new Line(p2, p4);
        linesOfTheRectangle[3] = new Line(p3, p4);
        for (int i = 0; i < 4; i++) {
            points.add(i, line.intersectionWith(linesOfTheRectangle[i]));
        }
        return points;
    }

    /**
     * method: getWidth.
     *
     * @return the width field
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * method: getHeight.
     *
     * @return the height field
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * method: getUpperLeft.
     *
     * @return the upperLeft field
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
