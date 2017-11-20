import edu.princeton.cs.algs4.StdOut;
import junit.framework.TestCase;
import java.util.Comparator;


/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class PointTest extends TestCase {

    public PointTest() {
    }
    
    public void testSlopeTo() {
        Point a = new Point(0, 0);
        
        // The slope is defined to be
        // +0.0 if the line segment connecting the two points is horizontal
        assertEquals(0.0, a.slopeTo(new Point(5, 0)));
        // Double.POSITIVE_INFINITY if the line segment is vertical
        assertEquals(Double.POSITIVE_INFINITY, a.slopeTo(new Point(0, 8)));
        // Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
        assertEquals(Double.NEGATIVE_INFINITY, a.slopeTo(new Point(0, 0)));
        
        assertEquals(1.0, a.slopeTo(new Point(1, 1)));
        assertEquals(0.5, a.slopeTo(new Point(2, 1)));
        assertEquals(2.0, a.slopeTo(new Point(1, 2)));
    }
    
    public void testCompareTo() {
     Point a = new Point(0, 0);
        
     assertEquals(-1, a.compareTo(new Point(0, 1)));
     assertEquals(-1, a.compareTo(new Point(1, 1)));
     assertEquals(0, a.compareTo(new Point(0, 0)));
     assertEquals(1, a.compareTo(new Point(-1, 0)));
     assertEquals(1, a.compareTo(new Point(-1, -1)));
    }
    
    public void testComparator() {
        Point a = new Point(0, 0);
        Comparator<Point> comparator = a.slopeOrder();
        
        assertEquals(0, comparator.compare(new Point(2, 2), new Point(1, 1)));
        assertEquals(1, comparator.compare(new Point(1, 1), new Point(2, 1)));
        assertEquals(-1, comparator.compare(new Point(2, 1), new Point(1, 1)));
    }
   
}
