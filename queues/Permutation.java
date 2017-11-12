/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation 3 < distinct.txt
 *  Dependencies: Iterator.java, StdIn.java, StdOut.java
 * 
 *  Name: Jonathan Experton
 *  Date: nov. 11 2017
 *  Purpose: Permutation client implementation
 * 
 ******************************************************************************/

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readString());
        
        Iterator iterator = queue.iterator();
        
        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            StdOut.println(iterator.next());
    }
}