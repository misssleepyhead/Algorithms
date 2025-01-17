/**
 * 1.5.14
 * uses the same basic strategy as weighted quick-union but keeps track of tree height and
 * always links the shorter tree to the taller one.
 */

public class WQUbyHeight {
    private int[] parent;
    private int[] height;
    private int count;

    public WQUbyHeight(int N) {
        parent = new int[N];
        height = new int[N];
        count = N;

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            height[i] = 0;
        }
    }

    public int count() {
        return count;
    }

    public int root(int x) {
        int root = x;

        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[x] != x) {
            int p = parent[x];
            parent[x] = root;
            x = p;
        }
        return root;

    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int proot = root(p);
        int qroot = root(q);
        if(qroot==proot){
            return;
        }

        // Union by height: Attach shorter tree under taller tree
        if (height[proot] < height[qroot]) {
            parent[proot] = qroot;
        } else if (height[proot] > height[qroot]) {
            parent[qroot] = proot;
        } else {
            // If heights are the same, choose one as the new root and increment height
            parent[qroot] = proot;
            height[proot]++;
        }

        count--; // Decrease the number of components
    }

    public static void main(String[] args) {
        WQUbyHeight uf = new WQUbyHeight(10);

        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(4, 5);
        uf.union(2, 5);

        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(1, 6)); // false
        System.out.println(uf.count());
    }
}
