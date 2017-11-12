/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    
 *  Dependencies: Iterator.java, NoSuchElementException.java, StdRandom.java
 * 
 *  Name: Jonathan Experton
 *  Date: nov. 11 2017
 *  Purpose: Deque implementation
 * 
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] stack;
    private int size = 0;

    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private int current = 0;
        private int[] indexes = new int[size];
        
        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++)
                indexes[i] = i;
                
            StdRandom.shuffle(indexes);
        }

        public boolean hasNext() { 
            return current < size;
        }

        public void remove() { 
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current >= indexes.length)
                throw new NoSuchElementException();

            return stack[indexes[++current - 1]];
        }
    }
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        stack = (Item[]) new Object[1];
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = stack[i];
        stack = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }  

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }  

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        
        if (size == stack.length) 
            resize(2 * stack.length);
        
        stack[++size - 1] = item;
    } 
    
    private int getRandomIndex() {
        if (size == 1)
            return 0;
        
        return StdRandom.uniform(0, size - 1);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        int i = getRandomIndex();
    
        Item item = stack[i];
        
        size--;
        
        // replace the dequeued item with the last item of the queue
        // to avoid null values when i < size and keep dequeue() and next() constant
        if (size > 0)
            stack[i] = stack[size];
        
        if (size > 0 && size == stack.length / 4) 
            resize(stack.length / 2);
        
        return item;
    }     

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        return stack[getRandomIndex()];
    }  

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    } 

    // unit testing (optional)
    public static void main(String[] args)   {
    // intentionally left emtpy 
    }
}