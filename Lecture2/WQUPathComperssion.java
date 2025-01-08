
/** Weight Quick union with path compression*/


public class WQUPathComperssion {
    private int[] id;
    private int[] size;

    public WQUPathComperssion(int N) {
        id=new int[N];
        size = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
            size[i]=1;
        }
    }
    // keep upward looking until the id is itself
//    private int root(int i){
//        while(id[i]!=i){
//            id[i] = id[id[i]]; // Path compression: Point to grandparent
//            // id[i] = root(id[i]); // Recursively find root and compress path
//            i=id[i];
//
//        }
//        return i;
//    }

    // two pass implement
    private int root(int i) {
        int root = i;

        // First pass: Find the root
        while (id[root] != root) {
            root = id[root];
        }

        // Second pass: Path compression, point all nodes directly to the root
        while (id[i] != i) {
            int parent = id[i];
            id[i] = root; // Point to the root
            i = parent;   // Move to the next node in the path
        }

        return root;
    }

    public boolean connected(int p, int q){
        return root(p)==root(p);
    }

    public void union(int p, int q){
        int proot = root(p);
        int qroot = root(q);
        if(proot==qroot){
            return;
        }
        if(size[p]<size[q]){
            id[proot]=qroot;
            size[qroot] +=size[proot];
        }else{
            size[proot] +=size[qroot];
            id[qroot]=proot;
        }

    }

    public int find(int p){
        return root(p);
    }

    public int count(){
        return id.length;
    }
}
