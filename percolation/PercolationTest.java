import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class PercolationTest extends TestCase {
    public Percolation percolator;

    public PercolationTest() {
        percolator = new Percolation(3);
    }

    public void testNumberOfOpenSitesStartsWithZero() {
        assertEquals(percolator.numberOfOpenSites(), 0);
    }

    public void testSiteStartsClosed() {
        assertFalse(percolator.isOpen(1, 1));
    }

    public void testSiteCannotStartFull() {
        assertFalse(percolator.isFull(1, 1));
    }

    public void testSitesCanBeOpened() {
        percolator.open(1, 1);
        assertEquals(percolator.numberOfOpenSites(), 1);
        assertTrue(percolator.isOpen(1, 1));

        // Open a second one
        percolator.open(1, 2);
        assertEquals(percolator.numberOfOpenSites(), 2);
        assertTrue(percolator.isOpen(1, 2));

        // Already opened
        percolator.open(1, 2);
        assertEquals(percolator.numberOfOpenSites(), 2);
        assertTrue(percolator.isOpen(1, 2));
    }

    public void testSitesInFirstRowAreAlwaysFull() {
        percolator.open(1, 2);
        assertTrue(percolator.isFull(1, 2));

        percolator.open(1, 3);
        assertTrue(percolator.isFull(1, 3));

        percolator.open(3, 3);
        assertFalse(percolator.isFull(3, 3));
    }

    public void testAdjacentSitesConnects() {
        // Connects with top
        percolator.open(1, 2);
        percolator.open(2, 2);
        assertTrue(percolator.isFull(2, 2));

        // Connects with bottom
        percolator.open(1, 2);
        percolator.open(2, 2);
        assertTrue(percolator.isFull(2, 2));
    }

    public void testSystemPercolatesTopToBottom() {
        percolator.open(1, 1);
        assertFalse(percolator.percolates());
        percolator.open(2, 1);
        assertFalse(percolator.percolates());
        percolator.open(2, 2);
        percolator.open(2, 3);
        assertFalse(percolator.percolates());
        assertFalse(percolator.percolates());
        percolator.open(3, 3);
        assertTrue(percolator.percolates());
    }

    public void testSystemPercolatesBottomToTop() {
        percolator.open(3, 2);
        assertFalse(percolator.percolates());
        percolator.open(2, 2);
        assertFalse(percolator.percolates());
        percolator.open(1, 2);
        assertTrue(percolator.percolates());
    }

    /**
     * Test client (optional).
     */
    public static void main(String[] args) {
    }
}
