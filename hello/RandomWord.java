import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String champion = null;
        while (!StdIn.isEmpty()) {
            i++;
            String s = StdIn.readString();
            if (StdRandom.bernoulli((double) 1 / i)) champion = s;
        }
        StdOut.println(champion);
    }
}
