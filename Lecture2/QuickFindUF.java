import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/** Implement quick-find
 * Using Integer array to represent the data structure
 * if two object have the same id, means they are in the same component*/
public class QuickFindUF {
    private int[] id;
    private int arrayAccesses; // Tracks the number of array accesses for a single operation
    private int total;         // Cumulative number of array accesses
    private int operationCounts; // Number of operations performed
    private int components;

    public QuickFindUF(int N){
        components=N;
        operationCounts = 0;
        total = 0;
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.setPenRadius(0.005);


    }
    // Change all elements from the component of p to q's
    public void union(int p, int q){
        arrayAccesses=0;
        operationCounts++;
        int pid = id[p];
        int qid=id[q];
        if(pid==qid){
            return;
        }
        for(int i=0;i<id.length;i++){
            arrayAccesses+=1;
            if(id[i]==pid){
                id[i]=qid;
                arrayAccesses++;
            }
        }
        components--;
        total+=arrayAccesses;
        draw(arrayAccesses);
    }

    public boolean connected(int p, int q){
        return id[p]==id[q];
    }

    public int find(int p){
        total+=1;
        return id[p];
    }

    public int count(){
        return id.length;
    }
    private void draw(int cost){
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(operationCounts, cost);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(operationCounts, total/operationCounts); // Cumulative cost

    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
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
