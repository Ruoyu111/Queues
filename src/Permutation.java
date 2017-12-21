import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rq.enqueue(str);
        }
        while (k > 0) {
            System.out.println(rq.dequeue());
            k--;
        }
    }
}
