import edu.princeton.cs.algs4.StdOut;
import junit.framework.TestCase;
import java.util.Iterator;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class DequeTest extends TestCase {

    public DequeTest() {
    }

    public void testIterator() {
        Deque<Integer> deque = new Deque<Integer>();
        Iterator iterator = deque.iterator();
        
        assertFalse(iterator.hasNext());
    }
    
    public void testAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        Integer element1 = 1;
        deque.addFirst(element1);
        Integer element2 = 2;
        deque.addFirst(element2);
        Iterator iterator = deque.iterator();
        
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next() == element2);
        assertTrue(iterator.next() == element1);
    }
    
    public void testRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        Integer element1 = 1;
        deque.addFirst(element1);
        Integer element2 = 2;
        deque.addFirst(element2);
        
        assertTrue(deque.size() == 2);
        assertTrue(deque.removeFirst() == element2);
        assertTrue(deque.size() == 1);
        assertTrue(deque.removeFirst() == element1);
        assertTrue(deque.size() == 0);
        assertTrue(deque.isEmpty());
    }
    
    public void testAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        Integer element1 = 1;
        deque.addLast(element1);
        Integer element2 = 2;
        deque.addLast(element2);
        Iterator iterator = deque.iterator();
        
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next() == element1);
        assertTrue(iterator.next() == element2);
    }
    
    public void testRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        Integer element1 = 1;
        deque.addLast(element1);
        Integer element2 = 2;
        deque.addLast(element2);
        
        assertTrue(deque.size() == 2);
        assertTrue(deque.removeLast() == element2);
        assertTrue(deque.size() == 1);
        assertTrue(deque.removeLast() == element1);
        assertTrue(deque.size() == 0);
        assertTrue(deque.isEmpty());
    }
    
    public void testMultipleOperations1() {
        Deque<Integer> deque = new Deque<Integer>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertTrue(deque.removeLast() == 1);
        deque.addFirst(4);
        assertTrue(deque.removeLast() == 4);
        assertTrue(deque.isEmpty());
        deque.addFirst(7);
        deque.addFirst(8);
        assertTrue(deque.removeLast() == 7);
        assertFalse(deque.isEmpty());
        assertTrue(deque.removeLast() == 8);
    }
    
    public void testMultipleOperations2() {
        Deque<Integer> deque = new Deque<Integer>();
         deque.addFirst(0);
         deque.addFirst(1);
         deque.addFirst(2);
         deque.addFirst(3);
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addFirst(6);
         assertTrue(deque.removeLast() == 0);
         deque.addFirst(8);
         assertTrue(deque.removeLast() == 1);
    }
        
}