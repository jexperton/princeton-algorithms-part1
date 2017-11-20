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
    
    private Point[] pointSet;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException();
        }
        pointSet = points;
        
        Arrays.sort(pointSet);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }
    
    // the line segments
    public LineSegment[] segments() {
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        
        for (int p = 0; p < pointSet.length; p++) {
            for (int q = p + 1; q < pointSet.length; q++) {
                double slope = pointSet[p].slopeTo(pointSet[q]);
                for (int r = q + 1; r < pointSet.length; r++) {
                    if (slope == pointSet[q].slopeTo(pointSet[r])) {
                        for (int s = r + 1; s < pointSet.length; s++) {
                            if (slope == pointSet[r].slopeTo(pointSet[s]) ) {
                                segmentsList.add(new LineSegment(pointSet[p], 
                                                                 pointSet[s]));
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

 