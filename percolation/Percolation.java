/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation input.txt
 *  Dependencies: WeightedQuickUnionUF.java
 * 
 *  Name: Jonathan Experton
 *  Date: oct. 25 2017
 *  Purpose: Percolation implementation
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // the n-length of the grid
    private final int length;
    private final int virtualTop;
    private final int virtualBottom;
    // the sites states where 0 is blocked and 1 is open
    private boolean[] sitesState;
    private int openSitesCount;
    // the sites connections
    private final WeightedQuickUnionUF sites;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        
        length = n;
        virtualTop = n * n;
        virtualBottom = virtualTop + 1;
        sitesState = new boolean[n * n];
        openSitesCount = 0;
        sites = new WeightedQuickUnionUF(n * n + 2);
    }

    // convert a cell position to an array index
    private int convertCoordinatesToIndex(int row, int col) {
        if (row > length || col > length)
            throw new IllegalArgumentException("Coordinates are out of range");
        return (row - 1) * length + col - 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException();
        
        if (isOpen(row, col))
            return;
        
        openSitesCount += 1;
        
        int cellIndex = convertCoordinatesToIndex(row, col);
        
        sitesState[cellIndex] = true;

        // if there's no site above, connect to the virtual top 
        if (row == 1)
            sites.union(cellIndex, virtualTop);
        // else if the site above is open, connect to it 
        else if (isOpen(row - 1, col))
            sites.union(cellIndex, convertCoordinatesToIndex(row - 1, col));

        // if there's no site below, connect to the virtual bottom
        if (row == length)
            sites.union(cellIndex, virtualBottom);
        // else if the site below is open, connect to it
        else if (isOpen(row + 1, col))
            sites.union(cellIndex, convertCoordinatesToIndex(row + 1, col));

        // there's an site on the left, connect to it
        if (col > 1 && isOpen(row, col - 1))
            sites.union(cellIndex, convertCoordinatesToIndex(row, col - 1));

        // there's an open site on the rigth, connect to it
        if (col < length && isOpen(row, col + 1))
            sites.union(cellIndex, convertCoordinatesToIndex(row, col + 1));
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException();
        return sitesState[convertCoordinatesToIndex(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException();
        return sites.connected(convertCoordinatesToIndex(row, col), virtualTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(virtualTop, virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // intentionally left emtpy
    }
}
