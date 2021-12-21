import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class KdTree {
    private Node root;

    private static class Node {
        private Point2D point;      // the point
        private boolean isVertical; // the axis-aligned rectangle corresponding to this node
        private Node lbTree = null; // the left/bottom subtree
        private Node rtTree = null; // the right/top subtree

        public Node(Point2D point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
        }
    }

    public int size() {
        return 1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Point2D p) {
        return true;
    }

    public void insert(Point2D insertedPoint) {
        if (insertedPoint == null) throw new IllegalArgumentException();
        if (root == null) root = new Node(insertedPoint, true);
        else insert(root, insertedPoint);
        StdOut.print(".");
    }

    private void insert(Node parent, Point2D insertedPoint) {
        if (parent == null || insertedPoint == null) throw new IllegalArgumentException();
        if (parent.point.equals(insertedPoint)) return;
        if (parent.isVertical)
            if (insertedPoint.x() > parent.point.x())
                if (parent.rtTree == null) parent.rtTree = new Node(insertedPoint, false);
                else insert(parent.rtTree, insertedPoint);
            else {
                if (parent.lbTree == null) parent.lbTree = new Node(insertedPoint, false);
                else insert(parent.lbTree, insertedPoint);
            }
        else {
            if (insertedPoint.y() > parent.point.y())
                if (parent.rtTree == null) parent.rtTree = new Node(insertedPoint, true);
                else insert(parent.rtTree, insertedPoint);
            else {
                if (parent.lbTree == null) parent.lbTree = new Node(insertedPoint, true);
                else insert(parent.lbTree, insertedPoint);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        return new ArrayList<>();
    }

    public Point2D nearest(Point2D p) {
        return p;
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

    private Double takeLowest(Double a, Double b) {
        if (a == null && b == null) throw new IllegalArgumentException();
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) >= 0 ? b : a;
    }

    private Double takeGreatest(Double a, Double b) {
        if (a == null && b == null) throw new IllegalArgumentException();
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static void main(String[] args) {

    }
}