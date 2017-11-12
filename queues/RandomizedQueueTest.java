import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import junit.framework.TestCase;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class RandomizedQueueTest extends TestCase {

    public RandomizedQueueTest() {
    }

    public void testIterator() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Iterator iterator = queue.iterator();
        
        assertFalse(iterator.hasNext());
    }
    
    public void testEnqueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        Iterator iterator = queue.iterator();
        
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next() != null);
        assertTrue(iterator.next() != null);
        assertFalse(iterator.hasNext());
        /*
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        }).next();
        */
    }
    
    public void testDequeue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.size() == 2);
        queue.dequeue();
        assertTrue(queue.size() == 1);
        queue.dequeue();
        assertTrue(queue.size() == 0);
    }
    
    public void testSample() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        int count = StdRandom.uniform(10, 100);
        
        for (int i = 0; i < count; i++)
            queue.enqueue(StdRandom.uniform(1, 100));
        
        for (int i = 0; i < count * 10; i++)
            assertTrue(queue.sample() != null);
        
    }
        
}