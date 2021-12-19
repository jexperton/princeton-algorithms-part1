/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private ArrayList<Board> solution;

    public Solver(Board board) {
        if (board == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(board, null));

        MinPQ<SearchNode> twinPq = new MinPQ<SearchNode>();
        twinPq.insert(new SearchNode(board.twin(), null));

        while (!pq.isEmpty()) {
            SearchNode deque = pq.delMin();
            SearchNode twinDeque = twinPq.delMin();

            if (twinDeque.board.isGoal()) break;
            for (Board twinNeighbor : twinDeque.board.neighbors()) {
                if (!twinDeque.hasEqualAncestor(twinNeighbor)) {
                    twinPq.insert(new SearchNode(twinNeighbor, twinDeque));
                }
            }

            if (deque.board.isGoal()) {
                this.solution = deque.getPathToBoard(new ArrayList<Board>());
                break;
            }
            for (Board neighbor : deque.board.neighbors()) {
                if (!deque.hasEqualAncestor(neighbor)) {
                    pq.insert(new SearchNode(neighbor, deque));
                }
            }
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        public final Board board;
        public final SearchNode parent;
        public final int moves;
        public final int manhattan;

        public SearchNode(Board board, SearchNode parent) {
            this.board = board;
            this.parent = parent;
            this.moves = parent == null ? 0 : parent.moves + 1;
            this.manhattan = this.board.manhattan();
        }

        public int compareTo(SearchNode y) {
            if (this.manhattan + this.moves == y.manhattan + y.moves) return 0;
            return this.manhattan + this.moves > y.manhattan + y.moves ? 1 : -1;
        }

        public boolean hasEqualAncestor(Board neighbor) {
            if (this.parent == null) return false;
            if (this.parent.board.equals(neighbor)) return true;
            return false;
        }

        public ArrayList<Board> getPathToBoard(ArrayList<Board> path) {
            path.add(this.board);
            if (this.parent == null) return path;
            return this.parent.getPathToBoard(path);
        }
    }

    public int moves() {
        if (!this.isSolvable()) return -1;
        return this.solution.size() - 1;
    }

    public boolean isSolvable() {
        return this.solution != null;
    }

    public Iterable<Board> solution() {
        if (this.solution == null) return null;
        ArrayList<Board> reversedCopy = new ArrayList<Board>(this.solution.size());
        for (int i = this.solution.size() - 1; i >= 0; i--)
            reversedCopy.add(this.solution.get(i));
        return reversedCopy;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();

        Board initial = new Board(tiles);

        // solve the puzzleboard
        long startTime = System.nanoTime();
        Solver solver = new Solver(initial);
        long endTime = System.nanoTime();

        double duration = Math.floor((double) (endTime - startTime) / 1000000) / 1000;

        // print solution to standard output
        if (!solver.isSolvable()) StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
        StdOut.println("\nDone in " + duration + "s");
    }
}
