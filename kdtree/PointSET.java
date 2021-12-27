import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private final SET<Point2D> values = new SET<>();

    // construct an empty set of points
    public PointSET() {
    }

    // number of points in the set
    public int size() {
        return this.values.size();
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return this.values.contains(p);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        this.values.add(p);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> range = new SET<>();
        for (Point2D p : this.values)
            if (p.y() >= rect.ymin() && p.y() <= rect.ymax() && p.x() >= rect.xmin()
                    && p.x() <= rect.xmax())
                range.add(p);
        return range;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Point2D nearest = null;
        for (Point2D n : this.values)
            if (nearest == null || n.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
                nearest = n;
        return nearest;
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : this.values) {
            StdDraw.setPenRadius(0.01D);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(p.x(), p.y());
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // empty on purpose
    }
}
