import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.function.Function;

public class KdTree {
    private Node root;
    private int size = 0;

    private static class Node {
        private final Point2D point;      // the point
        private final boolean isVertical; // the axis orientation corresponding to this node
        private Node lbTree = null; // the left/bottom subtree
        private Node rtTree = null; // the right/top subtree

        public Node(Point2D point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node findNode(Point2D searchPoint) {
        if (searchPoint == null) throw new IllegalArgumentException();
        return findNode(root, true, searchPoint);
    }

    private Node findNode(Node node, boolean isVertical, Point2D searchPoint) {
        if (searchPoint == null) throw new IllegalArgumentException();
        if (node == null) return null;
        if (node.point.equals(searchPoint)) return node;

        Function<Point2D, Double> getXY = isVertical ? Point2D::x : Point2D::y;

        if (getXY.apply(searchPoint) < getXY.apply(node.point) && node.lbTree != null)
            return findNode(node.lbTree, !isVertical, searchPoint);
        else if (getXY.apply(searchPoint) >= getXY.apply(node.point) && node.rtTree != null)
            return findNode(node.rtTree, !isVertical, searchPoint);
        return null;
    }

    public boolean contains(Point2D searchPoint) {
        if (searchPoint == null) throw new IllegalArgumentException();
        return findNode(searchPoint) != null;
    }

    public void insert(Point2D insertedPoint) {
        if (insertedPoint == null) throw new IllegalArgumentException();
        insert(root, true, insertedPoint);
    }

    private void insert(Node parent, boolean isVertical, Point2D insertedPoint) {
        if (insertedPoint == null) throw new IllegalArgumentException();
        if (parent == null) {
            root = new Node(insertedPoint, isVertical);
            size = 1;
            return;
        }
        if (parent.point.equals(insertedPoint)) return;

        Function<Point2D, Double> getXY = isVertical ? Point2D::x : Point2D::y;

        if (getXY.apply(insertedPoint) < getXY.apply(parent.point))
            if (parent.lbTree != null) insert(parent.lbTree, !isVertical, insertedPoint);
            else {
                parent.lbTree = new Node(insertedPoint, !isVertical);
                size++;
            }
        else {
            if (parent.rtTree != null) insert(parent.rtTree, !isVertical, insertedPoint);
            else {
                parent.rtTree = new Node(insertedPoint, !isVertical);
                size++;
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        return range(root, true, rect, new ArrayList<>());
    }

    private ArrayList<Point2D> range(
            Node node,
            boolean isVertical,
            RectHV rect,
            ArrayList<Point2D> pointsInRange
    ) {
        if (node == null) return pointsInRange;
        if (rect.contains(node.point)) pointsInRange.add(node.point);

        Function<Point2D, Double> getPointXY = isVertical ? Point2D::x : Point2D::y;
        Function<RectHV, Double> getRectMin = isVertical ? RectHV::xmin : RectHV::ymin;
        Function<RectHV, Double> getRectMax = isVertical ? RectHV::xmax : RectHV::ymax;

        if (node.lbTree != null && getRectMin.apply(rect) < getPointXY.apply(node.point))
            range(node.lbTree, !isVertical, rect, pointsInRange);
        if (node.rtTree != null && getRectMax.apply(rect) >= getPointXY.apply(node.point))
            range(node.rtTree, !isVertical, rect, pointsInRange);

        return pointsInRange;
    }

    public Point2D nearest(Point2D searchPoint) {
        return nearest(root, searchPoint, null);
    }

    private Point2D nearest(
            Node node,
            Point2D searchPoint,
            Point2D nearestPoint
    ) {
        if (searchPoint == null) throw new IllegalArgumentException();
        if (node == null) return nearestPoint;
        if (nearestPoint == null) nearestPoint = node.point;
        double nearestDistance = searchPoint.distanceSquaredTo(nearestPoint);
        boolean shouldTakeLeft = node.isVertical
                                 ? searchPoint.x() < node.point.x()
                                 : searchPoint.y() < node.point.y();
        double pointDistance = searchPoint.distanceSquaredTo(node.point);
        if (pointDistance < nearestDistance) {
            nearestPoint = node.point;
            nearestDistance = pointDistance;
        }

        if (shouldTakeLeft) {
            Point2D nearestLeft = nearest(node.lbTree, searchPoint, nearestPoint);
            double nearestLeftDistance = searchPoint.distanceSquaredTo(nearestLeft);
            if (nearestLeftDistance < nearestDistance) {
                nearestPoint = nearestLeft;
                nearestDistance = nearestLeftDistance;
            }
            // |a-b| = |b-a| -> (a-b)² = (b-a)²
            if (getPointToAxisDistance(node, searchPoint) < nearestDistance) {
                // might worth checking the opposite tree
                Point2D nearestOpposite = nearest(node.rtTree, searchPoint, nearestPoint);
                double nearestOppositeDistance = searchPoint.distanceSquaredTo(nearestOpposite);
                if (nearestOppositeDistance < nearestDistance) {
                    nearestPoint = nearestOpposite;
                }
            }
        }
        else {
            Point2D nearestRight = nearest(node.rtTree, searchPoint, nearestPoint);
            double nearestRightDistance = searchPoint.distanceSquaredTo(nearestRight);
            if (nearestRightDistance < nearestDistance) {
                nearestPoint = nearestRight;
                nearestDistance = nearestRightDistance;
            }

            if (getPointToAxisDistance(node, searchPoint) < nearestDistance) {
                // might worth checking the opposite tree
                Point2D nearestOpposite = nearest(node.lbTree, searchPoint, nearestPoint);
                double nearestOppositeDistance = searchPoint.distanceSquaredTo(nearestOpposite);
                if (nearestOppositeDistance < nearestDistance) {
                    nearestPoint = nearestOpposite;
                }
            }
        }

        return nearestPoint;
    }

    private double getPointToAxisDistance(Node node, Point2D searchPoint) {
        return node.isVertical
               ? Math.pow(searchPoint.x() - node.point.x(), 2)
               : Math.pow(searchPoint.y() - node.point.y(), 2);
    }


    public void draw() {
        draw(root, true, new Point2D[0]);
    }

    private void draw(
            Node node,
            boolean isVertical,
            Point2D[] ancestors
    ) {
        if (node == null || node.point == null) return;
        StdDraw.setPenRadius();
        StdDraw.setPenColor(isVertical ? StdDraw.RED : StdDraw.BLUE);
        Function<Point2D, Double> getValue = isVertical ? Point2D::y : Point2D::x;
        Point2D start = new Point2D(node.point.x(), 0.0);
        Point2D end = new Point2D(node.point.x(), 1.0);

        if (ancestors.length > 0) {
            start = findStart(node.point, ancestors, getValue);
            end = findEnd(node.point, ancestors, getValue);
        }

        if (!start.equals(end)) start.drawTo(end);

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(node.point.x(), node.point.y());

        Point2D[] family = new Point2D[ancestors.length + 1];
        family[0] = node.point;
        for (int i = 0; i < ancestors.length; i++)
            family[i + 1] = ancestors[i];

        draw(node.lbTree, !isVertical, family);
        draw(node.rtTree, !isVertical, family);
    }

    private static Point2D findStart(
            Point2D point,
            Point2D[] ancestors,
            Function<Point2D, Double> getValue
    ) {
        if (getValue.apply(point) == point.y())
            return new Point2D(point.x(), ancestors[0].y());
        return new Point2D(ancestors[0].x(), point.y());
    }

    private static Point2D findEnd(
            Point2D point,
            Point2D[] ancestors,
            Function<Point2D, Double> getValue
    ) {
        Point2D closest = null;
        double pointXY = getValue.apply(point);
        double parentXY = getValue.apply(ancestors[0]);

        for (int i = 1; i < ancestors.length; i++) {
            // evaluate candidates with same orientation only: n+2, n+4, etc.
            if (i % 2 != 0) continue;
            double ancestorXY = getValue.apply(ancestors[i]);
            // skip if parent and candidate are on the same side of point (lt or br)
            if ((pointXY < parentXY && pointXY < ancestorXY)
                    || (pointXY >= parentXY && pointXY >= ancestorXY)) continue;
            if (closest == null)
                closest = ancestors[i];
            else if (Math.abs(pointXY - ancestorXY) < Math.abs(pointXY - getValue.apply(closest)))
                closest = ancestors[i];
        }
        if (closest != null) StdOut.println(closest);
        if (pointXY == point.y()) {
            if (closest != null) return new Point2D(point.x(), closest.y());
            return new Point2D(point.x(), parentXY <= pointXY ? 1.0 : 0.0);
        }
        else {
            if (closest != null) return new Point2D(closest.x(), point.y());
            return new Point2D(parentXY <= pointXY ? 1.0 : 0.0, point.y());
        }
    }

    public static void main(String[] args) {
        // empty on purpose
    }
}