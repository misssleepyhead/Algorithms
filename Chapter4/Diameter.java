import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interview question 2:
 * Find Diameter in tree (connected, no cycle graph)
 * Find center in tree
 */
public class Diameter {
    private final int endPointA;
    private final int endPointB;
    private List<Integer> path; // vertices on diameter
    private final int length;   // number of edges on the diameter


    public Diameter(Graph G) {
        // 1. Do the first BFS from an arbitrary vertex to find the farthest A
        BreadthFirstPaths bfs0 = new BreadthFirstPaths(G, 0);
        endPointA = farthestVertex(G, bfs0);

        // 2. Do the second bfs from the endpoint A to find the farthest vertex B
        BreadthFirstPaths bfsA = new BreadthFirstPaths(G, endPointA);
        endPointB = farthestVertex(G, bfsA);

        // reconstruct diameter path end point a to b from bfsA
        path = new ArrayList<>();
        Iterable<Integer> paths = bfsA.pathTo(endPointB);
        for (int w : paths) {
            path.add(w);
        }
        length = bfsA.distTo(endPointB);

    }

    public List<Integer> center() {
        List<Integer> central = new ArrayList<>();
        int steps = length() / 2;
        if (length % 2 == 0) { // even = single center
            central.add(path().get(steps));
        } else { // odd: two centers
            central.add(path().get(steps));
            central.add(path().get(steps + 1));

        }
        return central;

    }

    public List<Integer> path() {
        return Collections.unmodifiableList(path);
    }

    public int getEndPointA() {
        return endPointA;
    }

    public int getEndPointB() {
        return endPointB;
    }

    public int length() {
        return length;
    }

    private int farthestVertex(Graph G, BreadthFirstPaths bfs) {
        int maxDist = -1, endV = -1;
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v) && bfs.distTo(v) > maxDist) {
                maxDist = bfs.distTo(v);
                endV = v;
            }
        }
        return endV;
    }
}
