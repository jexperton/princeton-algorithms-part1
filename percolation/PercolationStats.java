/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats 200 1000
 *  Dependencies: Percolation.java StdOut.java StdRandom.java StdStats.java
 * 
 *  Name: Jonathan Experton
 *  Date: oct. 25 2017
 *  Purpose: Show Percolation.java implementation's statistics
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static int n;
    private static int trials;
    private static double[] mean;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        mean = new double[trials];
    }

    // sample mean of percolation threshold
    public static double mean() {
        return StdStats.mean(mean);
    }

    // sample standard deviation of percolation threshold
    public static double stddev() {
        return StdStats.stddev(mean);
    }

    // low  endpoint of 95% confidence interval
    public static double confidenceLo() {
        return mean() - 1.96 * (stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public static double confidenceHi() {
        return mean() + 1.96 * (stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);

        mean = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }
            }
            mean[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
        StdOut.println("mean\t\t\t= " + mean());
        StdOut.println("stddev\t\t\t= " + stddev());
        StdOut.println("95% confidence interval\t= [" + confidenceLo() + ", " + confidenceHi() + "]");
    }
}
