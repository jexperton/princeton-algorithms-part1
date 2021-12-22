import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;
import junit.framework.TestCase;

@SuppressWarnings("CheckStyle")
public class KdTreeTest extends TestCase {

    public KdTreeTest() {

    }

    private static Point2D[] generatePoints(int size) {
        Point2D[] points = new Point2D[size];
        for (int i = 0; i < size; ++i) {
            double x = StdRandom.uniform(0.0D, 1.0D);
            double y = StdRandom.uniform(0.0D, 1.0D);
            points[i] = new Point2D(x, y);
        }
        return points;
    }

    public void testIsEmpty() {
        KdTree tree = new KdTree();
        Point2D pointA = new Point2D(0.2, 0.2);
        assertEquals(tree.isEmpty(), true);
        tree.insert(pointA);
        assertEquals(tree.isEmpty(), false);
    }

    public void testSize() {
        KdTree tree = new KdTree();
        Point2D[] points = KdTreeTest.generatePoints(10000);
        for (Point2D p : points) {
            tree.insert(p);
        }
        assertEquals(tree.size(), 10000);
    }

    public void testNonDegeneratePoints() {
        KdTree tree = new KdTree();
        Point2D pointA = new Point2D(0.2, 0.2);
        assertEquals(tree.size(), 0);
        tree.insert(pointA);
        assertEquals(tree.size(), 1);
        tree.insert(pointA);
        assertEquals(tree.size(), 1);
    }

    public void testContains() {
        KdTree tree = new KdTree();
        for (Point2D p : KdTreeTest.generatePoints(10000)) {
            tree.insert(p);
            assertEquals(tree.contains(p), true);
        }
    }

    public void testRange() {
        Point2D[] points = KdTreeTest.generatePoints(100);
        KdTree tree = new KdTree();
        RectHV rect = new RectHV(0.3, 0.3, 0.6, 0.6);
        for (Point2D p : points)
            tree.insert(p);
        for (Point2D p : tree.range(rect))
            assertEquals(rect.contains(p), true);
    }


    /**
     * Test client (optional).
     */
    public static void main(String[] args) {
        KdTreeTest kdTreeTest = new KdTreeTest();
        kdTreeTest.testIsEmpty();
        kdTreeTest.testSize();
        kdTreeTest.testNonDegeneratePoints();
        kdTreeTest.testContains();
    }
}

