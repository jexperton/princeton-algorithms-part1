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
    
    private Point[] pointSet;
        
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException();
        }
        pointSet = points;
        
        // Arrays.sort(pointSet);
    }
        
    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }
        
    // the line segments
    public LineSegment[] segments() {
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        
        for (int i = 0; i < pointSet.length; i++) {
            Arrays.sort(pointSet, pointSet[i].slopeOrder());
            ArrayList<Point> collinearPoints = new ArrayList<Point>();
            
            for (int j = 0; j < pointSet.length - 1; j++) {
                if (pointSet[i].compareTo(pointSet[j]) == 0)
                    continue;
                
                if (collinearPoints.size() == 0) {
                    collinearPoints.add(pointSet[j]);
                    continue;
                }
                
                if (pointSet[i].slopeTo(pointSet[j]) == pointSet[i].slopeTo(collinearPoints.get(0)))
                    collinearPoints.add(pointSet[j]);
            }
            
            if (collinearPoints.size() < 3)
                continue;
            
            collinearPoints.add(pointSet[i]);
            
            Point[] collinears = collinearPoints.toArray(new Point[collinearPoints.size()]);
            Arrays.sort(collinears);
            
            segmentsList.add(new LineSegment(collinears[0], collinears[collinears.length - 1]));
                
        } 
        
        StdOut.println(segmentsList.toArray(new LineSegment[segmentsList.size()]));
      
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