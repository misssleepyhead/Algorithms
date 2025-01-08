import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/** Weight quick union
 * Keep track of the size of the component
 * when union, connect the smaller component to the larger one*/
public class WeightedQUUF {
    private int[] id;
    private int[] size;

    private int arrayAccesses; // did not init in the constructor, since its to track the number of array accesses for single operation
    private int total;
    private int operationCounts;
    
    public WeightedQUUF(int N) {
        operationCounts=0;
        total=0;
        id=new int[N];
        size = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
            size[i]=1;
        }

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.setPenRadius(0.005);
    }
    // keep upward looking until the id is itself
    private int root(int i){

        while(id[i]!=i){
            i=id[i];
            arrayAccesses +=2;
        }

        return i;
    }

    public boolean connected(int p, int q){
        return root(p)==root(q);
    }

    public void union(int p, int q){
        arrayAccesses=0;
        int proot = root(p);
        int qroot = root(q);
        if(proot==qroot){
            return;
        }
        if(size[p]<size[q]){
            id[proot]=qroot;
            size[qroot] +=size[proot];
            arrayAccesses+=2;
        }else{
            size[proot] +=size[qroot];
            id[qroot]=proot;
            arrayAccesses+=2;

        }
        total+=arrayAccesses;
        draw(arrayAccesses);


    }

    private void draw(int cost){
        operationCounts++;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(operationCounts, cost);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(operationCounts, total/operationCounts); // Cumulative cost

    }

    public int find(int p){
        arrayAccesses = 0; // Reset for this operation
        int result = root(p);
        total += arrayAccesses;
        draw(arrayAccesses);
        return result;
    }

    public int count(){
        return id.length;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQUUF uf = new WeightedQUUF(n);
        System.out.println(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
