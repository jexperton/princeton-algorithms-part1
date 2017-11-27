/******************************************************************************
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    
    private final int n;
    private final Point[] points;
        
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0)
            throw new IllegalArgumentException();
        
        this.n = points.length;
        this.points = new Point[this.n];
        
        for (int i = 0; i < this.n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
                
            this.points[i] = points[i];
        }
        
        Arrays.sort(this.points);
        
        for (int i = 1; i < this.n; i++)
            if (this.points[i - 1].compareTo(this.points[i]) == 0)
                throw new IllegalArgumentException();
    }
        
    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }
        
    // the line segments
    public LineSegment[] segments() {
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        
        for (int  i = 0; i < this.n; i++) {
            Point center = this.points[i];
            Point[] candidates = Arrays.copyOf(this.points, this.n);
            
            for (int k = i; k < this.n - 1; k++)
                candidates[k] = this.points[k + 1];
            
            Arrays.sort(candidates, 0, this.n - 1, center.slopeOrder());
            
            int start = 0;

            for (int j = 1; j < this.n; j++) {
                if (j == this.n - 1 || center.slopeTo(candidates[start]) != center.slopeTo(candidates[j])) {
                    if (j - start > 2 && candidates[start].compareTo(center) > 0)
                        segmentsList.add(new LineSegment(center, candidates[j - 1]));
                    start = j;
                }
            }
        }
      
        return segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}