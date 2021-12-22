import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;
import junit.framework.TestCase;

@SuppressWarnings("CheckStyle")
public class PointSETTest extends TestCase {

    public PointSETTest() {

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

    public void testRange() {
        Point2D[] points = PointSETTest.generatePoints(100);
        PointSET brute = new PointSET();
        RectHV rect = new RectHV(0.3, 0.3, 0.6, 0.6);
        for (Point2D p : points)
            brute.insert(p);
        for (Point2D p : brute.range(rect))
            assertEquals(rect.contains(p), true);
    }


    /**
     * Test client (optional).
     */
    public static void main(String[] args) {
        PointSETTest pointSETtest = new PointSETTest();
        pointSETtest.testRange();
    }
}

