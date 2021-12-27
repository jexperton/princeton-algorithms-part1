import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.function.Function;

public class KdTree {
    private Node root;
    private int size = 0;

    private static class Node {
        private final Point2D point; // the point
        private Node lbTree = null;  // the left/bottom subtree
        private Node rtTree = null;  // the right/top subtree

        public Node(Point2D point) {
            this.point = point;
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
            root = new Node(insertedPoint);
            size = 1;
            return;
        }
        if (parent.point.equals(insertedPoint)) return;

        Function<Point2D, Double> getXY = isVertical ? Point2D::x : Point2D::y;

        if (getXY.apply(insertedPoint) < getXY.apply(parent.point))
            if (parent.lbTree != null) insert(parent.lbTree, !isVertical, insertedPoint);
            else {
                parent.lbTree = new Node(insertedPoint);
                size++;
            }
        else {
            if (parent.rtTree != null) insert(parent.rtTree, !isVertical, insertedPoint);
            else {
                parent.rtTree = new Node(insertedPoint);
                size++;
            }
        }
    }

    public Iterable<Point2D> range(RectHV selection) {
        if (selection == null) throw new IllegalArgumentException();
        return range(root, true, selection, new ArrayList<>());
    }

    private ArrayList<Point2D> range(
            Node node,
            boolean isVertical,
            RectHV selection,
            ArrayList<Point2D> pointsInRange
    ) {
        if (node == null) return pointsInRange;
        if (selection.contains(node.point)) pointsInRange.add(node.point);

        Function<Point2D, Double> getPointXY = isVertical ? Point2D::x : Point2D::y;
        Function<RectHV, Double> getRectMin = isVertical ? RectHV::xmin : RectHV::ymin;
        Function<RectHV, Double> getRectMax = isVertical ? RectHV::xmax : RectHV::ymax;

        if (node.lbTree != null && getRectMin.apply(selection) < getPointXY.apply(node.point))
            range(node.lbTree, !isVertical, selection, pointsInRange);
        if (node.rtTree != null && getRectMax.apply(selection) >= getPointXY.apply(node.point))
            range(node.rtTree, !isVertical, selection, pointsInRange);

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
        draw(root, true, new RectHV(0.0, 0.0, 1.0, 1.0));
    }

    private void draw(Node node, boolean isVertical, RectHV subGrid) {
        if (node == null || node.point == null) return;

        StdDraw.setPenRadius();
        StdDraw.setPenColor(isVertical ? StdDraw.RED : StdDraw.BLUE);

        if (isVertical) {
            new RectHV(node.point.x(), subGrid.ymin(), node.point.x(), subGrid.ymax()).draw();
            draw(node.lbTree, false,
                 new RectHV(subGrid.xmin(), subGrid.ymin(), node.point.x(), subGrid.ymax()));
            draw(node.rtTree, false,
                 new RectHV(node.point.x(), subGrid.ymin(), subGrid.xmax(), subGrid.ymax()));
        }
        else {
            new RectHV(subGrid.xmin(), node.point.y(), subGrid.xmax(), node.point.y()).draw();
            draw(node.lbTree, true,
                 new RectHV(subGrid.xmin(), subGrid.ymin(), subGrid.xmax(), node.point.y()));
            draw(node.rtTree, true,
                 new RectHV(subGrid.xmin(), node.point.y(), subGrid.xmax(), subGrid.ymax()));
        }

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(node.point.x(), node.point.y());
    }

    public static void main(String[] args) {
        // empty on purpose
    }
}