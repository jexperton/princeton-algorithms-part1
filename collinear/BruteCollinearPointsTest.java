import edu.princeton.cs.algs4.StdOut;
import junit.framework.TestCase;
import java.util.Comparator;
import java.util.Arrays;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class BruteCollinearPointsTest extends TestCase {

    private BruteCollinearPoints bruteCollinearPoints;
    
    public BruteCollinearPointsTest() {
        Point[] points = {
            new Point (2, 2),
            new Point (6, 6),
            new Point (9, 4),
            new Point (2, 7),
            new Point (0, 1),
            new Point (1, 2),
            new Point (0, 0),
            new Point (5, 5)
        };
        bruteCollinearPoints = new BruteCollinearPoints(points);
    }
    
    public void testSegments() {
        // LineSegment segment = new LineSegment(new Point (0, 0), new Point (6, 6));
        // LineSegment[] expected = {segment};

        // assertEquals(expected, bruteCollinearPoints.segments());
    }
}