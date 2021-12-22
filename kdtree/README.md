*See the Assessment Guide for information on how to interpret this report.*

## ASSESSMENT SUMMARY
```
Compilation:  PASSED
API:          PASSED

SpotBugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 1 warning)

Correctness:  26/35 tests passed
Memory:       16/16 tests passed
Timing:       42/42 tests passed

Aggregate score: 84.57%
[ Compilation: 5%, API: 5%, Style: 0%, Correctness: 60%, Timing: 10%, Memory: 20% ]
```
## ASSESSMENT DETAILS
```
The following files were submitted:
----------------------------------
12K Dec 22 06:26 KdTree.java
2.1K Dec 22 06:26 PointSET.java


********************************************************************************
*  COMPILING
********************************************************************************


% javac PointSET.java
*-----------------------------------------------------------

% javac KdTree.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
PointSET:

KdTree:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS
********************************************************************************


% spotbugs *.class
*-----------------------------------------------------------


================================================================


% pmd .
*-----------------------------------------------------------


================================================================


% checkstyle *.java
*-----------------------------------------------------------

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------
[WARN] PointSET.java:59:34: The numeric literal '0.01' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 1 warning.

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of PointSET
*-----------------------------------------------------------
Running 8 total tests.

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m

Test 1: insert n random points; check size() and isEmpty() after each insertion
(size may be less than n because of duplicates)
* 5 random points in a 1-by-1 grid
* 50 random points in a 8-by-8 grid
* 100 random points in a 16-by-16 grid
* 1000 random points in a 128-by-128 grid
* 5000 random points in a 1024-by-1024 grid
* 50000 random points in a 65536-by-65536 grid
  ==> passed

Test 2: insert n random points; check contains() with random query points
* 1 random points in a 1-by-1 grid
* 10 random points in a 4-by-4 grid
* 20 random points in a 8-by-8 grid
* 10000 random points in a 128-by-128 grid
* 100000 random points in a 1024-by-1024 grid
* 100000 random points in a 65536-by-65536 grid
  ==> passed

Test 3: insert random points; check nearest() with random query points
* 10 random points in a 4-by-4 grid
* 15 random points in a 8-by-8 grid
* 20 random points in a 16-by-16 grid
* 100 random points in a 32-by-32 grid
* 10000 random points in a 65536-by-65536 grid
  ==> passed

Test 4: insert random points; check range() with random query rectangles
* 2 random points and random rectangles in a 2-by-2 grid
* 10 random points and random rectangles in a 4-by-4 grid
* 20 random points and random rectangles in a 8-by-8 grid
* 100 random points and random rectangles in a 16-by-16 grid
* 1000 random points and random rectangles in a 64-by-64 grid
* 10000 random points and random rectangles in a 128-by-128 grid
  ==> passed

Test 5: call methods before inserting any points
* size() and isEmpty()
* contains()
* nearest()
* range()
  ==> passed

Test 6: call methods with null argument
* insert()
* contains()
* range()
* nearest()
  ==> passed

Test 7: check intermixed sequence of calls to insert(), isEmpty(),
size(), contains(), range(), and nearest() with
probabilities (p1, p2, p3, p4, p5, p6, p7), respectively
* 10000 calls with random points in a 1-by-1 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
* 10000 calls with random points in a 16-by-16 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
* 10000 calls with random points in a 128-by-128 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
* 10000 calls with random points in a 1024-by-1024 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
* 10000 calls with random points in a 8192-by-8192 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
* 10000 calls with random points in a 65536-by-65536 grid
  and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  ==> passed

Test 8: check that two PointSET objects can be created at the same time
==> passed


Total: 8/8 tests passed!


================================================================
Testing correctness of KdTree
*-----------------------------------------------------------
Running 27 total tests.

In the tests below, we consider three classes of points and rectangles.

* Non-degenerate points: no two points (or rectangles) share either an
  x-coordinate or a y-coordinate

* Distinct points:       no two points (or rectangles) share both an
  x-coordinate and a y-coordinate

* General points:        no restrictions on the x-coordinates or y-coordinates
  of the points (or rectangles)

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m (inclusive).

Test 1a: insert points from file; check size() and isEmpty() after each insertion
* input0.txt
* input1.txt
* input5.txt
* input10.txt
  ==> passed

Test 1b: insert non-degenerate points; check size() and isEmpty() after each insertion
* 1 random non-degenerate points in a 1-by-1 grid
* 5 random non-degenerate points in a 8-by-8 grid
* 10 random non-degenerate points in a 16-by-16 grid
* 50 random non-degenerate points in a 128-by-128 grid
* 500 random non-degenerate points in a 1024-by-1024 grid
* 50000 random non-degenerate points in a 65536-by-65536 grid
  ==> passed

Test 1c: insert distinct points; check size() and isEmpty() after each insertion
* 1 random distinct points in a 1-by-1 grid
* 10 random distinct points in a 8-by-8 grid
* 20 random distinct points in a 16-by-16 grid
* 10000 random distinct points in a 128-by-128 grid
* 100000 random distinct points in a 1024-by-1024 grid
* 100000 random distinct points in a 65536-by-65536 grid
  ==> passed

Test 1d: insert general points; check size() and isEmpty() after each insertion
* 5 random general points in a 1-by-1 grid
* 10 random general points in a 4-by-4 grid
* 50 random general points in a 8-by-8 grid
* 100000 random general points in a 16-by-16 grid
* 100000 random general points in a 128-by-128 grid
* 100000 random general points in a 1024-by-1024 grid
  ==> passed

Test 2a: insert points from file; check contains() with random query points
* input0.txt
* input1.txt
    - failed on trial 88 of 10000
    - query point          = (0.5, 0.5)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.5 0.5

* input5.txt
    - failed on trial 923 of 10000
    - query point          = (0.5, 0.4)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6

* input10.txt
  ==> FAILED

Test 2b: insert non-degenerate points; check contains() with random query points
* 1 random non-degenerate points in a 1-by-1 grid
    - failed on trial 4 of 10000
    - query point          = (1.0, 1.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  1.0 1.0

* 5 random non-degenerate points in a 8-by-8 grid
    - failed on trial 63 of 10000
    - query point          = (0.0, 0.625)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.25 0.75
      B  0.0 0.625
      C  0.125 0.375
      D  1.0 1.0
      E  0.875 0.875

* 10 random non-degenerate points in a 16-by-16 grid
    - failed on trial 146 of 10000
    - query point          = (0.6875, 1.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.5 0.6875
      B  0.375 0.0
      C  0.1875 0.75
      D  0.8125 0.1875
      E  0.125 0.0625
      F  0.25 0.125
      G  0.6875 1.0
      H  0.625 0.8125
      I  0.0625 0.9375
      J  0.3125 0.25

* 20 random non-degenerate points in a 32-by-32 grid
    - failed on trial 31 of 10000
    - query point          = (1.0, 0.46875)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.71875 0.4375
      B  0.1875 0.96875
      C  0.34375 0.21875
      D  0.15625 0.6875
      E  0.9375 0.0625
      F  0.84375 0.5625
      G  0.75 0.8125
      H  0.21875 0.375
      I  0.125 0.625
      J  0.4375 0.125
      K  1.0 0.46875
      L  0.03125 0.15625
      M  0.28125 0.75
      N  0.875 0.1875
      O  0.0 0.3125
      P  0.78125 0.65625
      Q  0.09375 0.875
      R  0.59375 0.03125
      S  0.25 0.59375
      T  0.96875 0.90625

* 500 random non-degenerate points in a 1024-by-1024 grid
    - failed on trial 247 of 10000
    - query point          = (0.267578125, 0.9501953125)
    - student   contains() = false
    - reference contains() = true

* 10000 random non-degenerate points in a 65536-by-65536 grid
  ==> FAILED

Test 2c: insert distinct points; check contains() with random query points
* 1 random distinct points in a 1-by-1 grid
    - failed on trial 7 of 10000
    - query point          = (0.0, 0.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.0 0.0

* 10 random distinct points in a 4-by-4 grid
    - failed on trial 1 of 10000
    - query point          = (0.0, 0.0)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.25 0.0
      B  0.75 1.0
      C  1.0 0.5
      D  0.25 0.25
      E  0.0 0.5
      F  0.25 1.0
      G  0.0 0.25
      H  0.5 0.5
      I  1.0 0.25
      J  0.0 0.0

* 20 random distinct points in a 8-by-8 grid
    - failed on trial 4 of 10000
    - query point          = (0.625, 0.25)
    - student   contains() = false
    - reference contains() = true
    - sequence of points inserted:
      A  0.25 0.125
      B  0.5 0.125
      C  0.75 0.25
      D  0.875 0.75
      E  1.0 0.25
      F  0.875 1.0
      G  0.875 0.0
      H  0.5 0.375
      I  0.375 0.75
      J  0.25 0.5
      K  0.125 0.625
      L  0.0 0.375
      M  0.5 0.75
      N  0.0 0.25
      O  0.875 0.125
      P  0.5 0.875
      Q  1.0 0.75
      R  0.125 0.125
      S  0.625 0.625
      T  0.625 0.25

* 10000 random distinct points in a 128-by-128 grid
    - failed on trial 3 of 10000
    - query point          = (0.3515625, 0.1171875)
    - student   contains() = false
    - reference contains() = true

* 100000 random distinct points in a 1024-by-1024 grid
    - failed on trial 11 of 10000
    - query point          = (0.0693359375, 0.443359375)
    - student   contains() = false
    - reference contains() = true

* 100000 random distinct points in a 65536-by-65536 grid
  ==> FAILED

Test 2d: insert general points; check contains() with random query points
* 10000 random general points in a 1-by-1 grid
    - failed on trial 1 of 10000
    - query point          = (0.0, 0.0)
    - student   contains() = false
    - reference contains() = true

* 10000 random general points in a 16-by-16 grid
    - failed on trial 1 of 10000
    - query point          = (0.9375, 0.1875)
    - student   contains() = false
    - reference contains() = true

* 10000 random general points in a 128-by-128 grid
    - failed on trial 1 of 10000
    - query point          = (0.1015625, 0.296875)
    - student   contains() = false
    - reference contains() = true

* 10000 random general points in a 1024-by-1024 grid
    - failed on trial 173 of 10000
    - query point          = (0.5234375, 0.03125)
    - student   contains() = false
    - reference contains() = true

==> FAILED

Test 3a: insert points from file; check range() with random query rectangles
* input0.txt
* input1.txt
* input5.txt
* input10.txt
  ==> passed

Test 3b: insert non-degenerate points; check range() with random query rectangles
* 1 random non-degenerate points and random rectangles in a 2-by-2 grid
* 5 random non-degenerate points and random rectangles in a 8-by-8 grid
* 10 random non-degenerate points and random rectangles in a 16-by-16 grid
* 20 random non-degenerate points and random rectangles in a 32-by-32 grid
* 500 random non-degenerate points and random rectangles in a 1024-by-1024 grid
* 10000 random non-degenerate points and random rectangles in a 65536-by-65536 grid
  ==> passed

Test 3c: insert distinct points; check range() with random query rectangles
* 2 random distinct points and random rectangles in a 2-by-2 grid
* 10 random distinct points and random rectangles in a 4-by-4 grid
* 20 random distinct points and random rectangles in a 8-by-8 grid
* 100 random distinct points and random rectangles in a 16-by-16 grid
* 1000 random distinct points and random rectangles in a 64-by-64 grid
* 10000 random distinct points and random rectangles in a 128-by-128 grid
  ==> passed

Test 3d: insert general points; check range() with random query rectangles
* 5000 random general points and random rectangles in a 2-by-2 grid
* 5000 random general points and random rectangles in a 16-by-16 grid
* 5000 random general points and random rectangles in a 128-by-128 grid
* 5000 random general points and random rectangles in a 1024-by-1024 grid
  ==> passed

Test 3e: insert random points; check range() with tiny rectangles
enclosing each point
* 5 tiny rectangles and 5 general points in a 2-by-2 grid
* 10 tiny rectangles and 10 general points in a 4-by-4 grid
* 20 tiny rectangles and 20 general points in a 8-by-8 grid
* 5000 tiny rectangles and 5000 general points in a 128-by-128 grid
* 5000 tiny rectangles and 5000 general points in a 1024-by-1024 grid
* 5000 tiny rectangles and 5000 general points in a 65536-by-65536 grid
  ==> passed

Test 4a: insert points from file; check range() with random query rectangles
and check traversal of kd-tree
* input5.txt
* input10.txt
  ==> passed

Test 4b: insert non-degenerate points; check range() with random query rectangles
and check traversal of kd-tree
* 3 random non-degenerate points and 1000 random rectangles in a 4-by-4 grid
* 6 random non-degenerate points and 1000 random rectangles in a 8-by-8 grid
* 10 random non-degenerate points and 1000 random rectangles in a 16-by-16 grid
* 20 random non-degenerate points and 1000 random rectangles in a 32-by-32 grid
* 30 random non-degenerate points and 1000 random rectangles in a 64-by-64 grid
  ==> passed

Test 5a: insert points from file; check nearest() with random query points
* input0.txt
* input1.txt
* input5.txt
* input10.txt
  ==> passed

Test 5b: insert non-degenerate points; check nearest() with random query points
* 5 random non-degenerate points in a 8-by-8 grid
* 10 random non-degenerate points in a 16-by-16 grid
* 20 random non-degenerate points in a 32-by-32 grid
* 30 random non-degenerate points in a 64-by-64 grid
* 10000 random non-degenerate points in a 65536-by-65536 grid
  ==> passed

Test 5c: insert distinct points; check nearest() with random query points
* 10 random distinct points in a 4-by-4 grid
* 15 random distinct points in a 8-by-8 grid
* 20 random distinct points in a 16-by-16 grid
* 100 random distinct points in a 32-by-32 grid
* 10000 random distinct points in a 65536-by-65536 grid
  ==> passed

Test 5d: insert general points; check nearest() with random query points
* 10000 random general points in a 16-by-16 grid
* 10000 random general points in a 128-by-128 grid
* 10000 random general points in a 1024-by-1024 grid
  ==> passed

Test 6a: insert points from file; check nearest() with random query points
and check traversal of kd-tree
* input5.txt
* input10.txt
    - student   nearest() = (0.226, 0.577)
    - reference nearest() = (0.226, 0.577)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.2, 0.68)
    - sequence of points inserted:
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A C F D E
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A C F D
    - failed on trial 41 of 1000

==> FAILED

Test 6b: insert non-degenerate points; check nearest() with random query points
and check traversal of kd-tree
* 5 random non-degenerate points in a 8-by-8 grid
* 10 random non-degenerate points in a 16-by-16 grid
    - student   nearest() = (0.3125, 0.875)
    - reference nearest() = (0.3125, 0.875)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.25, 0.6875)
    - sequence of points inserted:
      A  1.0 0.9375
      B  0.875 0.625
      C  0.125 0.125
      D  0.75 0.0625
      E  0.4375 0.1875
      F  0.625 0.8125
      G  0.3125 0.875
      H  0.0 0.0
      I  0.6875 0.5
      J  0.5625 0.25
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A B F G C D E I J H
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A B F G C D E H
    - failed on trial 22 of 1000

* 20 random non-degenerate points in a 32-by-32 grid
    - student   nearest() = (0.34375, 0.28125)
    - reference nearest() = (0.34375, 0.28125)
    - performs incorrect traversal of kd-tree during call to nearest()
    - query point = (0.15625, 0.34375)
    - sequence of points inserted:
      A  0.78125 0.40625
      B  0.8125 1.0
      C  0.96875 0.03125
      D  0.875 0.46875
      E  0.34375 0.28125
      F  0.375 0.0
      G  0.3125 0.59375
      H  0.6875 0.3125
      I  0.90625 0.71875
      J  0.40625 0.125
      K  0.03125 0.0625
      L  0.65625 0.96875
      M  0.5 0.5
      N  0.71875 0.4375
      O  0.53125 0.5625
      P  0.625 0.9375
      Q  0.09375 0.8125
      R  0.46875 0.15625
      S  0.84375 0.65625
      T  0.75 0.1875
    - student sequence of kd-tree nodes involved in calls to Point2D methods:
      A E G Q H L M O F K
    - reference sequence of kd-tree nodes involved in calls to Point2D methods:
      A E G Q H L M F K
    - failed on trial 21 of 1000

* 30 random non-degenerate points in a 64-by-64 grid
    - student   nearest() = (0.578125, 0.609375)
    - reference nearest() = (0.578125, 0.609375)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 16
    - number of reference entries = 13
    - entry 11 of the two sequences are not equal
    - student   entry 11 = (0.46875, 0.046875)
    - reference entry 11 = (0.90625, 0.078125)

    - failed on trial 22 of 1000

* 50 random non-degenerate points in a 128-by-128 grid
    - student   nearest() = (0.4296875, 0.65625)
    - reference nearest() = (0.4296875, 0.65625)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 14
    - number of reference entries = 13
    - failed on trial 16 of 1000

* 1000 random non-degenerate points in a 2048-by-2048 grid
    - student   nearest() = (0.69677734375, 0.42919921875)
    - reference nearest() = (0.69677734375, 0.42919921875)
    - performs incorrect traversal of kd-tree during call to nearest()
    - number of student   entries = 33
    - number of reference entries = 31
    - entry 29 of the two sequences are not equal
    - student   entry 29 = (0.65380859375, 0.4765625)
    - reference entry 29 = (0.81689453125, 0.47802734375)

    - failed on trial 3 of 1000

==> FAILED

Test 7: check with no points
* size() and isEmpty()
* contains()
* nearest()
* range()
  ==> passed

Test 8: check that the specified exception is thrown with null arguments
* argument to insert() is null
* argument to contains() is null
* argument to range() is null
    - throws wrong exception when calling range() with a null argument
    - throws a java.lang.NullPointerException
    - should throw a java.lang.IllegalArgumentException

* argument to nearest() is null
  ==> FAILED

Test 9a: check intermixed sequence of calls to insert(), isEmpty(),
size(), contains(), range(), and nearest() with probabilities
(p1, p2, p3, p4, p5, p6), respectively
* 20000 calls with non-degenerate points in a 1-by-1 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with non-degenerate points in a 16-by-16 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with non-degenerate points in a 128-by-128 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with non-degenerate points in a 1024-by-1024 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with non-degenerate points in a 8192-by-8192 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with non-degenerate points in a 65536-by-65536 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  ==> passed

Test 9b: check intermixed sequence of calls to insert(), isEmpty(),
size(), contains(), range(), and nearest() with probabilities
(p1, p2, p3, p4, p5, p6), respectively
* 20000 calls with distinct points in a 1-by-1 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with distinct points in a 16-by-16 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with distinct points in a 128-by-128 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with distinct points in a 1024-by-1024 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with distinct points in a 8192-by-8192 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with distinct points in a 65536-by-65536 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  ==> passed

Test 9c: check intermixed sequence of calls to insert(), isEmpty(),
size(), contains(), range(), and nearest() with probabilities
(p1, p2, p3, p4, p5, p6), respectively
* 20000 calls with general points in a 1-by-1 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 5 of 20000
    - student   contains() = false
    - reference contains() = true
    - sequence of operations was:
      st.insert(0.0, 0.0)
      st.isEmpty()  ==>  false
      st.contains((1.0, 1.0))  ==>  false
      st.contains((1.0, 1.0))  ==>  false
      st.contains((0.0, 0.0))  ==>  false

* 20000 calls with general points in a 16-by-16 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 66 of 20000
    - student   contains() = false
    - reference contains() = true

* 20000 calls with general points in a 128-by-128 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 837 of 20000
    - student   contains() = false
    - reference contains() = true

* 20000 calls with general points in a 1024-by-1024 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
    - failed on trial 5735 of 20000
    - student   contains() = false
    - reference contains() = true

* 20000 calls with general points in a 8192-by-8192 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
* 20000 calls with general points in a 65536-by-65536 grid
  and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  ==> FAILED

Test 10: insert n random points into two different KdTree objects;
check that repeated calls to size(), contains(), range(),
and nearest() with the same arguments yield same results
* 10 random general points in a 4-by-4 grid
* 20 random general points in a 8-by-8 grid
    - failed on trial 155 of 1000
    - p = (0.875, 0.125)
    - first call to st2.contains(p) = false
    - last  call to st2.contains(p) = true

* 100 random general points in a 128-by-128 grid
    - failed on trial 680 of 1000
    - p = (0.765625, 0.9921875)
    - first call to st1.contains(p) = false
    - last  call to st1.contains(p) = true

* 1000 random general points in a 65536-by-65536 grid
  ==> FAILED


Total: 18/27 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point2D
*-----------------------------------------------------------
Memory of Point2D object = 32 bytes

================================================================



Analyzing memory of RectHV
*-----------------------------------------------------------
Memory of RectHV object = 48 bytes

================================================================



Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          264                264
=> passed        2          360                360
=> passed        5          648                648
=> passed       10         1128               1128
=> passed       25         2568               2568
=> passed      100         9768               9768
=> passed      400        38568              38568
=> passed      800        76968              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)

================================================================



Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          112                160
=> passed        2          192                288
=> passed        5          432                672
=> passed       10          832               1312
=> passed       25         2032               3232
=> passed      100         8032              12832
=> passed      400        32032              51232
=> passed      800        64032             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 80.00 n + 32.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)

================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.


Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1638215         
=> passed   320000    1514989         
=> passed   640000    1434005         
=> passed  1280000     969714         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000     549110         
=> passed   320000     553551         
=> passed   640000     460536         
=> passed  1280000     481161         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       6024         
=> passed    20000       1877         
=> passed    40000        823         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       6832         
=> passed    20000       2061         
=> passed    40000        875         
==> 3/3 tests passed

Total: 14/14 tests passed!


================================================================



Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.


Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1460442               0.0              22.1              21.1              21.6         
=> passed   320000    1485963               0.0              22.5              21.5              22.0         
=> passed   640000    1327595               0.0              24.0              23.0              23.5         
=> passed  1280000     994228               0.0              26.1              25.1              25.6         
==> 4/4 tests passed


Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000     905644              29.0              26.5               0.0         
=> passed    20000     933730              29.3              27.4               0.0         
=> passed    40000     880630              33.4              30.9               0.0         
=> passed    80000     814975              32.9              31.8               0.0         
=> passed   160000     683060              34.4              35.5               0.0         
=> passed   320000     655311              37.8              36.0               0.0         
=> passed   640000     543441              39.7              37.1               0.0         
=> passed  1280000     474400              40.5              39.9               0.0         
==> 8/8 tests passed


Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> passed    10000     489280               0.0              31.1              75.5              36.1         
=> passed    20000     512224               0.0              32.6              79.4              41.8         
=> passed    40000     421797               0.0              39.3              95.7              45.4         
=> passed    80000     417950               0.0              40.7              98.9              47.4         
=> passed   160000     344589               0.0              42.5             105.4              54.9         
=> passed   320000     297046               0.0              40.2              98.6              48.3         
=> passed   640000     257206               0.0              43.3             106.3              54.5         
=> passed  1280000     209070               0.0              47.0             114.6              52.2         
==> 8/8 tests passed


Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------
=> passed    10000   571717                  79.9                    0.0                    50.5              49.8         
=> passed    20000   565028                  87.5                    0.0                    55.5              54.5         
=> passed    40000   488841                 102.4                    0.0                    64.8              64.1         
=> passed    80000   448528                 104.3                    0.0                    67.0              64.5         
=> passed   160000   386334                 112.8                    0.0                    71.5              70.8         
=> passed   320000   318557                 117.2                    0.0                    75.3              72.8         
=> passed   640000   255428                 121.8                    0.0                    78.1              75.7         
=> passed  1280000   221559                 135.8                    0.0                    85.4              86.2         
==> 8/8 tests passed



Total: 28/28 tests passed!


================================================================



```