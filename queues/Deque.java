import java.util.Iterator;
    
public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int size = 0;
    
    private class Node {
        Node next, prev;
        Item item;
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { 
            return current != null; 
        }

        public void remove() { 
            /* not supported */ 
        }

        public Item next() {
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
        return first == null; 
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
        oldlast.next = last;
        
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        --size;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item item = last.item;
        last = last.prev;
        --size;
        return last.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }
}