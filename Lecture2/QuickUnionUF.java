
/**
 * Use id[] to represent components, two element has same root id if
 * they are in the same component*/

public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) {
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
    }
    // keep upward looking until the id is itself
    private int root(int i){
        while(id[i]!=i){
            i=id[i];
        }
        return i;
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
        id[proot]=qroot;
    }

    public int find(int p){
        return id[p];
    }

    public int count(){
        return id.length;
    }
}
