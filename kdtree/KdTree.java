import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

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
        return findNode(root, searchPoint);
    }

    private Node findNode(Node node, Point2D searchPoint) {
        if (searchPoint == null) throw new IllegalArgumentException();
        if (node == null) return null;
        if (node.point == searchPoint) return node;
        if (node.isVertical) {
            if (searchPoint.x() < node.point.x() && node.lbTree != null)
                return findNode(node.lbTree, searchPoint);
            else if (searchPoint.x() >= node.point.x() && node.rtTree != null)
                return findNode(node.rtTree, searchPoint);
        }
        else {
            if (searchPoint.y() < node.point.y() && node.lbTree != null)
                return findNode(node.lbTree, searchPoint);
            else if (searchPoint.y() >= node.point.y() && node.rtTree != null) {
                return findNode(node.rtTree, searchPoint);
            }
        }
        return null;
    }

    public boolean contains(Point2D searchPoint) {
        if (searchPoint == null) throw new IllegalArgumentException();
        return findNode(searchPoint) != null;
    }

    public void insert(Point2D insertedPoint) {
        if (insertedPoint == null) throw new IllegalArgumentException();
        if (root == null) {
            root = new Node(insertedPoint, true);
            size = 1;
        }
        else insert(root, insertedPoint);
    }

    private void insert(Node parent, Point2D insertedPoint) {
        if (parent == null || insertedPoint == null) throw new IllegalArgumentException();
        if (parent.point.equals(insertedPoint)) return;
        if (parent.isVertical)
            if (insertedPoint.x() < parent.point.x())
                if (parent.lbTree == null) {
                    parent.lbTree = new Node(insertedPoint, false);
                    size++;
                }
                else insert(parent.lbTree, insertedPoint);
            else {
                if (parent.rtTree == null) {
                    parent.rtTree = new Node(insertedPoint, false);
                    size++;
                }
                else insert(parent.rtTree, insertedPoint);
            }
        else {
            if (insertedPoint.y() < parent.point.y())
                if (parent.lbTree == null) {
                    parent.lbTree = new Node(insertedPoint, true);
                    size++;
                }
                else insert(parent.lbTree, insertedPoint);
            else {
                if (parent.rtTree == null) {
                    parent.rtTree = new Node(insertedPoint, true);
                    size++;
                }
                else insert(parent.rtTree, insertedPoint);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        return range(root, rect, new ArrayList<>());
    }

    private ArrayList<Point2D> range(
            Node node,
            RectHV rect,
            ArrayList<Point2D> pointsInRange
    ) {
        if (node == null) return pointsInRange;
        if (rect.contains(node.point)) pointsInRange.add(node.point);
        if (node.isVertical) {
            if (node.lbTree != null && rect.xmin() < node.point.x())
                range(node.lbTree, rect, pointsInRange);
            if (node.rtTree != null && rect.xmax() >= node.point.x())
                range(node.rtTree, rect, pointsInRange);
        }
        else {
            if (node.lbTree != null && rect.ymin() < node.point.y())
                range(node.lbTree, rect, pointsInRange);
            if (node.rtTree != null && rect.ymax() >= node.point.y())
                range(node.rtTree, rect, pointsInRange);
        }
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
        draw(root, null, null, null);
    }

    private void draw(Node node, Node parent, Node grandParent, Node grandGrandParent) {
        if (node == null || node.point == null) return;
        StdDraw.setPenRadius();

        Point2D parentPoint = parent == null ? null : parent.point;
        Point2D grandGrandParentPoint = grandGrandParent == null ? null : grandGrandParent.point;

        if (node.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            if (parentPoint == null)
                StdDraw.line(node.point.x(), 0.0, node.point.x(), 1.0);
            else if (grandGrandParentPoint == null) {
                if (node.point.y() < parentPoint.y())
                    StdDraw.line(node.point.x(), 0.0, node.point.x(), parent.point.y());
                else
                    StdDraw.line(node.point.x(), parent.point.y(), node.point.x(), 1.0);
            }
            else {
                if (node.point.y() < takeLowest(parentPoint.y(), grandGrandParentPoint.y()))
                    StdDraw.line(
                            node.point.x(),
                            0.0,
                            node.point.x(),
                            takeLowest(parentPoint.y(), grandGrandParentPoint.y()));
                else if (node.point.y() > takeGreatest(parentPoint.y(), grandGrandParentPoint.y()))
                    StdDraw.line(
                            node.point.x(),
                            takeGreatest(parentPoint.y(), grandGrandParentPoint.y()),
                            node.point.x(),
                            1.0);
                else
                    StdDraw.line(
                            node.point.x(),
                            parentPoint.y(),
                            node.point.x(),
                            grandGrandParentPoint.y());
            }
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            if (parentPoint == null)
                StdDraw.line(0.0, node.point.y(), 1.0, node.point.y());
            else if (grandGrandParentPoint == null) {
                if (node.point.x() < parentPoint.x())
                    StdDraw.line(0.0, node.point.y(), parent.point.x(), node.point.y());
                else
                    StdDraw.line(parent.point.x(), node.point.y(), 1.0, node.point.y());
            }
            else {
                if (node.point.x() < takeLowest(parentPoint.x(), grandGrandParentPoint.x()))
                    StdDraw.line(
                            0.0,
                            node.point.y(),
                            takeLowest(parentPoint.x(), grandGrandParentPoint.x()),
                            node.point.y());
                else if (node.point.x() > takeGreatest(parentPoint.x(), grandGrandParentPoint.x()))
                    StdDraw.line(
                            takeGreatest(parentPoint.x(), grandGrandParentPoint.x()),
                            node.point.y(),
                            1.0,
                            node.point.y());
                else
                    StdDraw.line(
                            parentPoint.x(),
                            node.point.y(),
                            grandGrandParentPoint.x(),
                            node.point.y());
            }
        }

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(node.point.x(), node.point.y());

        draw(node.lbTree, node, parent, grandParent);
        draw(node.rtTree, node, parent, grandParent);
    }

    private double takeLowest(double a, double b) {
        return a > b ? b : a;
    }

    private double takeGreatest(double a, double b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        // empty on purpose
    }
}