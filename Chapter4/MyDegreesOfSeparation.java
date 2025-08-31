import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;

/**
 * Book example 4.1
 */
public class MyDegreesOfSeparation {
    public static void main(String[] args) {
        MySymbolGraph sg = new MySymbolGraph(args[0], args[1]);
        Graph g = sg.G();
        String source = args[2];
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }
        int s = sg.index(source);
        MyBFSPaths bfs = new MyBFSPaths(g, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        System.out.println(" " + sg.name(v));
                    }
                } else {
                    System.out.println("Not connected.");
                }
            } else {
                System.out.println("not in database");
            }
        }
    }
}
