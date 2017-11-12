/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque input.txt
 *  Dependencies: Iterator.java, NoSuchElementException.java
 * 
 *  Name: Jonathan Experton
 *  Date: nov. 11 2017
 *  Purpose: Deque implementation
 * 
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;
    
public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int size = 0;
    
    private class Node {
        private Node next, prev;
        private Item item;
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { 
            return current != null; 
        }

        public void remove() { 
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null; 
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        
        if (size == 0)
            last = first;

        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
            
        if (oldlast != null)
            oldlast.next = last; 
        
        if (size == 0)
            first = last;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        
        Item item = first.item;
        first = first.next;
        
        if (size == 1) {
            last = null;
            first = null;
        }
        
        --size;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        
        Item item = last.item;
        last = last.prev;
        
        if (size == 1) {
            last = null;
            first = null;
        }
        
        --size;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }
}