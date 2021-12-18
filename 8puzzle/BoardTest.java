import junit.framework.TestCase;

@SuppressWarnings("CheckStyle")
public class BoardTest extends TestCase {

    public BoardTest() {

    }

    public void testToString() {
        int[][] args = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board board = new Board(args);

        assertEquals(board.toString(), "3\n 8 1 3\n 4 0 2\n 7 6 5");
    }

    public void testHamming() {
        int[][] args = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board board = new Board(args);

        assertEquals(board.hamming(), 5);
    }

    public void testManhattan() {
        int[][] args = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board board = new Board(args);

        assertEquals(board.manhattan(), 10);
    }

    public void testEquals() {
        int[][] args = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board board = new Board(args);
        int[][] equalArgs = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board equalBoard = new Board(equalArgs);
        int[][] notEqualArgs = new int[][] {
                new int[] { 8, 1, 6 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 3, 5 }
        };
        Board notEqualBoard = new Board(notEqualArgs);

        assertEquals(board.equals(equalBoard), true);
        assertEquals(board.equals(notEqualBoard), false);
    }

    public void testIsGoal() {
        int[][] notSolvedArgs = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board notSolvedBoard = new Board(notSolvedArgs);
        int[][] solvedArgs = new int[][] {
                new int[] { 1, 2, 3 },
                new int[] { 4, 5, 6 },
                new int[] { 7, 8, 0 }
        };
        Board solvedBoard = new Board(solvedArgs);

        assertEquals(notSolvedBoard.isGoal(), false);
        assertEquals(solvedBoard.isGoal(), true);
    }

    public void testNeighbors() {
        int i = 0;
        int[][] args = new int[][] {
                new int[] { 8, 1, 3 },
                new int[] { 4, 0, 2 },
                new int[] { 7, 6, 5 }
        };
        Board board = new Board(args);
        Iterable<Board> neightbors = board.neighbors();
        String[] out = {
                "3\n 8 1 3\n 4 2 0\n 7 6 5",
                "3\n 8 0 3\n 4 1 2\n 7 6 5",
                "3\n 8 1 3\n 0 4 2\n 7 6 5",
                "3\n 8 1 3\n 4 6 2\n 7 0 5"
        };

        for (Board neightbor : neightbors)
            assertEquals(neightbor.toString(), out[i++]);
    }

    public void testTwin() {
        int[][] args = new int[][] {
                new int[] { 1, 2, 3 },
                new int[] { 4, 5, 6 },
                new int[] { 7, 8, 0 }
        };
        Board board = new Board(args);

        assertEquals(board.twin().toString(), board.twin().toString());
        assertEquals(board.twin().hamming(), 2);

    }

    /**
     * Test client (optional).
     */
    public static void main(String[] args) {
        BoardTest board = new BoardTest();
        board.testToString();
        board.testHamming();
        board.testManhattan();
        board.testEquals();
        board.testIsGoal();
        board.testNeighbors();
        board.testTwin();
    }
}

