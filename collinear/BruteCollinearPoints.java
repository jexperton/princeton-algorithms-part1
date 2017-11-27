/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    
    private final int n;
    private final Point[] points;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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
        return this.segments().length;
    }
    
    // the line segments
    public LineSegment[] segments() {
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        
        for (int p = 0; p < this.n; p++) {
            for (int q = p + 1; q < this.n; q++) {
                double slope = this.points[p].slopeTo(this.points[q]);
                for (int r = q + 1; r < this.n; r++) {
                    if (slope == this.points[q].slopeTo(this.points[r])) {
                        for (int s = r + 1; s < this.n; s++) {
                            if (slope == this.points[r].slopeTo(this.points[s])) {
                                segmentsList.add(new LineSegment(this.points[p], this.points[s]));
                            }
                        }
                    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

 