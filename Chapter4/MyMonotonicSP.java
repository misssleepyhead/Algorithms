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
    public static final long INF = Long.MAX_VALUE / 4;

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


    /**
     * Final answer: min(increasing, decreasing) for each vertex.
     */
    public static long[] monotonicShortestPaths(int n, List<Edge> edges, int src) {
        long[] inc = increasingPass(n, edges, src);
        long[] dec = decreasingPass(n, edges, src);
        long[] ans = new long[n];
        for (int v = 0; v < n; v++) ans[v] = Math.min(inc[v], dec[v]);
        return ans;
    }

    // Strictly increasing edge weights along the path, re;ax edges in ascending order and find a best path
    public static long[] increasingPass(int n, List<Edge> edges, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // 1. sort edges by weight
        List<Edge> sorted = new ArrayList<>(edges);
        sorted.sort(Comparator.comparingInt(e -> e.weight)); // ascending

        int i = 0;
        int m = edges.size();

        // 2. process edges in batches of the same weight
        while (i < m) {
            int j = i;

            int currentWeight = edges.get(i).weight;
            List<Update> batchUpdates = new ArrayList<>();

            while (j < m && sorted.get(j).weight == currentWeight) {
                Edge e = sorted.get(j);

                // if src is reachable from a previous weight group
                if (dist[e.from] < INF) {
                    batchUpdates.add(new Update(e.to, dist[e.from] + currentWeight));
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

    /**
     * Best strictly decreasing monotonic distances from src. relax edges in descending order and find a best path
     */
    public static long[] decreasingPass(int n, List<Edge> edges, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        List<Edge> sorted = new ArrayList<>(edges);
        sorted.sort((a, b) -> Integer.compare(b.weight, a.weight)); // descending

        int i = 0;
        int m = sorted.size();
        while (i < m) {
            int currentweight = sorted.get(i).weight;

            int j = i;
            List<Update> pending = new ArrayList<>();
            while (j < m && sorted.get(j).weight == currentweight) {
                Edge e = sorted.get(j);
                if (dist[e.from] < INF) {
                    pending.add(new Update(e.to, dist[e.from] + currentweight));
                }
                j++;
            }

            for (Update up : pending) {
                if (up.newDist < dist[up.vertex]) dist[up.vertex] = up.newDist;
            }
            i = j;
        }
        return dist;

    }
}
