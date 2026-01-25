import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * interview question and 4.4.34
 * Given an edge-weighted digraph, find a monotonic shortest path from s to every other vertex.
 * A path is monotonic if the weight of every edge on the path is either strictly increasing or strictly decreasing.
 */
public class MyMonotonicSP {
    public static class Edge {
        public final int from, to;
        public final int weight;

        public Edge(int u, int v, int w) {
            this.from = u;
            this.to = v;
            this.weight = w;
        }
    }

    // helper class to store a potential update for a vertex
    static class Update {
        int vertex;
        long newDist;

        Update(int vertex, long newDist) {
            this.vertex = vertex;
            this.newDist = newDist;
        }

    }

    public static long[] findIncreasingPath(int n, List<Edge> edges, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;

        // 1. sort edges by weight
        edges.sort(Comparator.comparingInt(e -> e.weight));
        int i = 0;
        int m = edges.size();

        // 2. process edges in batches of the same weight
        while (i < m) {
            int j = 1;

            int currentWeight = edges.get(i).weight;
            List<Update> batchUpdates = new ArrayList<>();

            while (j < m && edges.get(j).weight == currentWeight) {
                Edge e = edges.get(j);

                // if src is reachable from a previous weight group
                if (dist[e.from] != Long.MAX_VALUE) {
                    batchUpdates.add(new Update(e.to, dist[e.from] + e.weight));
                }
                j++;
            }
            // 3. apply the update only after scanning the whole batch
            // this ensures the path is STRICTLY increasing
            for (Update update : batchUpdates) {
                if (update.newDist < dist[update.vertex]) {
                    dist[update.vertex] = update.newDist;
                }
            }
            i = j; // move to next weight group

        }
        return dist;
    }
}
