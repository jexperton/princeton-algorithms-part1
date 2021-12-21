import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
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
        for (Point2D p : brute.range(rect)) {
            StdOut.println(p.toString());
            assertEquals(p.x() <= rect.xmax(), true);
            assertEquals(p.x() >= rect.xmin(), true);
            assertEquals(p.y() <= rect.ymax(), true);
            assertEquals(p.y() >= rect.ymin(), true);

        }
    }


    /**
     * Test client (optional).
     */
    public static void main(String[] args) {
        PointSETTest pointSETtest = new PointSETTest();
        pointSETtest.testRange();
    }
}

